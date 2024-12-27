package com.baidubce.services.vod.v2;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableFileInputStream;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.internal.RestartableMultiByteArrayInputStream;
import com.baidubce.internal.RestartableNonResettableInputStream;
import com.baidubce.internal.RestartableResettableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.vod.v2.model.ComposeCreateRequest;
import com.baidubce.services.vod.v2.model.CreateMediaRequest;
import com.baidubce.services.vod.v2.model.FileUploadRequest;
import com.baidubce.services.vod.v2.model.GenericMediaRequest;
import com.baidubce.services.vod.v2.model.GenericMediaResponse;
import com.baidubce.services.vod.v2.model.GenericTaskRequest;
import com.baidubce.services.vod.v2.model.GenericTaskResponse;
import com.baidubce.services.vod.v2.model.ListByMarkerResponse;
import com.baidubce.services.vod.v2.model.MediaBasicInfo;
import com.baidubce.services.vod.v2.model.MediaCompleteUploadRequest;
import com.baidubce.services.vod.v2.model.MediaDetail;
import com.baidubce.services.vod.v2.model.MediaDwmDetectRequest;
import com.baidubce.services.vod.v2.model.MediaFetchRequest;
import com.baidubce.services.vod.v2.model.MediaListRequest;
import com.baidubce.services.vod.v2.model.MediaOutputDeleteRequest;
import com.baidubce.services.vod.v2.model.MediaProcessRequest;
import com.baidubce.services.vod.v2.model.MediaTaskBasicInfo;
import com.baidubce.services.vod.v2.model.MediaTaskDetail;
import com.baidubce.services.vod.v2.model.MediaTaskListRequest;
import com.baidubce.services.vod.v2.model.MediaUpdateRequest;
import com.baidubce.services.vod.v2.model.MediaUploadRequest;
import com.baidubce.services.vod.v2.model.MediaUploadResponse;
import com.baidubce.services.vod.v2.model.VodUploadProgressCallback;
import com.baidubce.util.HashUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Mimetypes;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VodClient extends AbstractBceClient {

    private static Logger logger = LoggerFactory.getLogger(VodClient.class);

    /**
     * The version information for VODv2 service APIs as URI prefix.
     */
    private static final String VERSION = "v2";

    private static final String MEDIAS = "medias";
    private static final String UPLOAD = "upload";
    private static final String COMPLETE_UPLOAD = "complete_upload";
    private static final String FETCH = "fetch";
    private static final String OUTPUT_DELETE = "output/delete";
    private static final String BAN = "ban";
    private static final String UNBAN = "unBan";
    private static final String PROCESS = "process";
    private static final String MEDIA_COMPOSE = "media_compose";
    private static final String MEDIA_DWM_DETECT = "media_dwm_detect";
    private static final String TASKS = "tasks";

    private static final String MARKER = "marker";
    private static final String MAX_SIZE = "maxSize";
    private static final String BEGIN_TIME = "beginTime";
    private static final String END_TIME = "endTime";

    private static final String HEADER_TRAFFIC_LIMIT = "x-bce-traffic-limit";

    private static final HttpResponseHandler[] RESPONSE_HANDLERS =
            new HttpResponseHandler[]{new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler()};

    public VodClient(VodClientConfiguration clientConfiguration) {
        super(clientConfiguration, RESPONSE_HANDLERS, clientConfiguration.isEnableHttpAsyncPut());
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<>();
        path.add(VERSION);
        if (pathVariables != null) {
            path.addAll(Arrays.asList(pathVariables));
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[0]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());
        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillPayload(request, bceRequest);
        }
        return request;
    }

    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson;
            try {
                requestJson = strJson.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
    }

    private List<byte[]> readAll(InputStream input, ObjectMetadata metadata) {
        List<byte[]> result = Lists.newArrayList();
        int bufferSize = this.getStreamBufferSize();
        long length = 0;
        for (; ; ) {
            byte[] buffer = new byte[bufferSize];
            result.add(buffer);
            int off = 0;
            while (off < bufferSize) {
                int count;
                try {
                    count = input.read(buffer, off, bufferSize - off);
                } catch (IOException e) {
                    throw new BceClientException("Fail to read data.", e);
                }
                if (count < 0) {
                    metadata.setContentLength(length);
                    return result;
                }
                length += count;
                off += count;
            }
        }
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream input) {
        if (input.markSupported()) {
            return new RestartableResettableInputStream(input);
        } else {
            return new RestartableNonResettableInputStream(input, this.getStreamBufferSize());
        }
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream input, VodUploadProgressCallback<?> progressCallback) {
        if (input.markSupported()) {
            return new RestartableResettableInputStream(input, progressCallback);
        } else {
            return new RestartableNonResettableInputStream(input, this.getStreamBufferSize(), progressCallback);
        }
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream input, Long bufferSize) {
        if (input.markSupported()) {
            return new RestartableResettableInputStream(input);
        } else {
            int size = bufferSize > this.getStreamBufferSize() ? this.getStreamBufferSize() : bufferSize.intValue();
            return new RestartableNonResettableInputStream(input, size);
        }
    }

    private RestartableInputStream wrapRestartableInputStream(InputStream input, Long bufferSize, VodUploadProgressCallback<?> progressCallback) {
        if (input.markSupported()) {
            return new RestartableResettableInputStream(input, progressCallback);
        } else {
            int size = bufferSize > this.getStreamBufferSize() ? this.getStreamBufferSize() : bufferSize.intValue();
            return new RestartableNonResettableInputStream(input, size, progressCallback);
        }
    }

    private int getStreamBufferSize() {
        return ((VodClientConfiguration) this.config).getStreamBufferSize();
    }

    private boolean isHttpsEnabled() {
        return ((VodClientConfiguration) this.config).isHttpsEnabled();
    }

    /**
     * Populates the specified request object with the appropriate headers from the ObjectMetadata object.
     *
     * @param request  The request to populate with headers.
     * @param metadata The metadata containing the header information to include in the request.
     */
    private static void populateRequestMetadata(InternalRequest request, ObjectMetadata metadata) {
        if (metadata.getContentType() != null) {
            request.addHeader(Headers.CONTENT_TYPE, metadata.getContentType());
        }
        if (metadata.getContentMd5() != null) {
            request.addHeader(Headers.CONTENT_MD5, metadata.getContentMd5());
        }
        if (metadata.getContentEncoding() != null) {
            request.addHeader(Headers.CONTENT_ENCODING, metadata.getContentEncoding());
        }
        if (metadata.getBceContentSha256() != null) {
            request.addHeader(Headers.BCE_CONTENT_SHA256, metadata.getBceContentSha256());
        }
        if (metadata.getContentDisposition() != null) {
            request.addHeader(Headers.CONTENT_DISPOSITION, metadata.getContentDisposition());
        }
        if (metadata.getETag() != null) {
            request.addHeader(Headers.ETAG, metadata.getETag());
        }
        if (metadata.getExpires() != null) {
            request.addHeader(Headers.EXPIRES, metadata.getExpires());
        }
        if (metadata.getCacheControl() != null) {
            request.addHeader(Headers.CACHE_CONTROL, metadata.getCacheControl());
        }
        if (metadata.getStorageClass() != null) {
            request.addHeader(Headers.BCE_STORAGE_CLASS, metadata.getStorageClass());
        }
        if (metadata.getxBceAcl() != null) {
            request.addHeader(Headers.BCE_ACL, String.valueOf(metadata.getxBceAcl()));
        }
        if (metadata.getxBceCrc() != null) {
            request.addHeader(Headers.BCE_CONTENT_CRC32, metadata.getxBceCrc());
        }
        if (metadata.getxBceCrc32c() != null) {
            request.addHeader(Headers.BCE_CONTENT_CRC32C, metadata.getxBceCrc32c());
        }

        Map<String, String> userMetadata = metadata.getUserMetadata();
        if (userMetadata != null) {
            for (Entry<String, String> entry : userMetadata.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    continue;
                }
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                if (key.length() + value.length() > 1024 * 32) {
                    throw new BceClientException("MetadataTooLarge");
                }
                request.addHeader(Headers.BCE_USER_METADATA_PREFIX + HttpUtils.normalize(key.trim()), HttpUtils.normalize(value));
            }
        }
    }

    /**
     * Create a simple upload request.
     *
     * @param name      The name of the media to be uploaded.
     * @param container The container of the media file.
     * @return A MediaUploadResponse object containing the upload information.
     */
    public MediaUploadResponse createUpload(String name, String container) {
        MediaUploadRequest mediaUploadRequest = new MediaUploadRequest().withName(name).withContainer(container);
        return createUpload(mediaUploadRequest);
    }

    /**
     * Create a multipart upload request.
     *
     * @param name      The name of the media to be uploaded.
     * @param container The container of the media file.
     * @param numParts  The number of parts to be uploaded.
     * @return A MediaUploadResponse object containing the upload information.
     */
    public MediaUploadResponse createUpload(String name, String container, int numParts) {
        MediaUploadRequest mediaUploadRequest = new MediaUploadRequest().withName(name).withContainer(container).withMultipartUpload(true).withNumParts(numParts);
        return createUpload(mediaUploadRequest);
    }

    /**
     * Create a media upload request.
     *
     * @param request The media upload request.
     * @return A MediaUploadResponse object containing the upload information.
     */
    public MediaUploadResponse createUpload(MediaUploadRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, MEDIAS, UPLOAD);
        MediaUploadResponse response = this.invokeHttpClient(internalRequest, MediaUploadResponse.class);
        if (!this.isHttpsEnabled()) {
            response.setUrls(response.getUrls().stream().map(url -> url.replace("https://", "http://")).collect(Collectors.toList()));
        }
        return response;
    }

    /**
     * Complete the simple upload of a media file.
     *
     * @param sessionKey The session key for the simple media upload key.
     * @return mediaId
     */
    public GenericMediaResponse completeUpload(String sessionKey) {
        MediaCompleteUploadRequest mediaCompleteUploadRequest = new MediaCompleteUploadRequest().withSessionKey(sessionKey);
        return completeUpload(mediaCompleteUploadRequest);
    }

    /**
     * Complete the upload of a media file.
     *
     * @param request The MediaCompleteUploadRequest object containing the necessary information for completing the upload.
     * @return mediaId
     */
    public GenericMediaResponse completeUpload(MediaCompleteUploadRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, MEDIAS, COMPLETE_UPLOAD);
        return this.invokeHttpClient(internalRequest, GenericMediaResponse.class);
    }

    /**
     * The Encapsulation of three-step uploading to VOD media.
     *
     * @param request   The request object for uploading a file.
     * @param name      The name of the media to be uploaded.
     * @param container The container to which the media will be uploaded.
     * @return mediaId
     */
    public GenericMediaResponse createMedia(FileUploadRequest request, String name, String container) {
        MediaUploadRequest mediaUploadRequest = new MediaUploadRequest().withName(name).withContainer(container);
        CreateMediaRequest createMediaRequest =
                new CreateMediaRequest().withMediaUploadRequest(mediaUploadRequest).withFileUploadRequest(request).withMediaCompleteUploadRequest(new MediaCompleteUploadRequest());
        return createMedia(createMediaRequest);
    }

    /**
     * The Encapsulation of three-step uploading to VOD media.
     *
     * @param request   The request object for uploading a file.
     * @param name      The name of the media to be uploaded.
     * @param container The container to which the media will be uploaded.
     * @param numParts  The number of parts to be uploaded.
     * @return mediaId
     */
    public GenericMediaResponse createMedia(FileUploadRequest request, String name, String container, int numParts) {
        MediaUploadRequest mediaUploadRequest = new MediaUploadRequest().withName(name).withContainer(container).withMultipartUpload(true).withNumParts(numParts);
        CreateMediaRequest createMediaRequest =
                new CreateMediaRequest().withMediaUploadRequest(mediaUploadRequest).withFileUploadRequest(request).withMediaCompleteUploadRequest(new MediaCompleteUploadRequest());
        return createMedia(createMediaRequest);
    }

    /**
     * The Encapsulation of three-step uploading to VOD media.
     *
     * @param createMediaRequest create media request, contains MediaUploadRequest, FileUploadRequest, MediaCompleteUploadRequest
     * @return mediaId
     */
    public GenericMediaResponse createMedia(CreateMediaRequest createMediaRequest) {
        checkNotNull(createMediaRequest, "createMediaRequest should be set");
        MediaUploadRequest mediaUploadRequest = createMediaRequest.getMediaUploadRequest();
        checkNotNull(mediaUploadRequest, "mediaUploadRequest should be set");
        FileUploadRequest fileUploadRequest = createMediaRequest.getFileUploadRequest();
        checkNotNull(fileUploadRequest, "fileUploadRequest should be set");
        MediaCompleteUploadRequest mediaCompleteUploadRequest = createMediaRequest.getMediaCompleteUploadRequest();
        checkNotNull(mediaCompleteUploadRequest, "mediaCompleteUploadRequest should be set");

        // create upload
        MediaUploadResponse mediaUploadResponse = createUpload(mediaUploadRequest);

        if (mediaUploadRequest.isMultipartUpload()) {
            // upload file parts
            mediaCompleteUploadRequest.setEtags(uploadLocalFilePart(createMediaRequest.getFileUploadRequest(), mediaUploadRequest.getNumParts(), mediaUploadResponse.getUrls()));
        } else {
            // upload file
            uploadLocalFile(fileUploadRequest, mediaUploadResponse.getUrls().get(0));
        }

        // complete upload
        mediaCompleteUploadRequest.setSessionKey(mediaUploadResponse.getSessionKey());
        mediaCompleteUploadRequest.setMultipartUpload(mediaUploadRequest.isMultipartUpload());
        return completeUpload(mediaCompleteUploadRequest);
    }

    public void uploadLocalFile(FileUploadRequest request, String uploadUrl) {
        URI uri;
        String fileName;
        try {
            uri = new URI(uploadUrl);
            fileName = URLDecoder.decode(uri.getPath(), "UTF-8");
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new BceClientException("Unable to parse uploadUrl", e);
        }

        InternalRequest internalRequest = new InternalRequest(HttpMethodName.PUT, uri);

        ObjectMetadata metadata = request.getObjectMetadata();
        InputStream input = request.getInputStream();
        if (request.getFile() != null) {
            File file = request.getFile();

            if (file.length() > 5 * 1024 * 1024 * 1024L) {
                BceServiceException bse = new BceServiceException("Your proposed upload exceeds the maximum allowed object size.");
                bse.setStatusCode(400);
                bse.setErrorCode("EntityTooLarge");
                bse.setErrorType(BceServiceException.ErrorType.Client);
                throw bse;
            }

            // Always set the content length, even if it's already set
            if (metadata.getContentLength() < 0) {
                metadata.setContentLength(file.length());
            }

            if (metadata.getContentLength() == file.length()) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    metadata.setBceContentSha256(new String(Hex.encodeHex(HashUtils.computeSha256Hash(fileInputStream))));
                } catch (Exception e) {
                    throw new BceClientException("Unable to calculate SHA-256 hash", e);
                } finally {
                    try {
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (Exception e) {
                        logger.warn("The inputStream accured error");
                    }
                }
            }
            try {
                if (request.getProgressCallback() == null) {
                    internalRequest.setContent(new RestartableFileInputStream(file));
                } else {
                    internalRequest.setContent(new RestartableFileInputStream(file, request.getProgressCallback()));
                }
            } catch (FileNotFoundException e) {
                throw new BceClientException("Unable to find file to upload", e);
            }
        } else {
            checkNotNull(input, "Either file or inputStream should be set.");
            if (metadata.getContentLength() < 0) {
                logger.warn("No content length specified for stream data. Trying to read them all into memory.");
                List<byte[]> data = this.readAll(input, metadata);
                if (request.getProgressCallback() == null) {
                    internalRequest.setContent(new RestartableMultiByteArrayInputStream(data, metadata.getContentLength()));
                } else {
                    internalRequest.setContent(new RestartableMultiByteArrayInputStream(data, metadata.getContentLength(), request.getProgressCallback()));
                }

            } else if (input instanceof RestartableInputStream) {
                internalRequest.setContent((RestartableInputStream) input);
            } else {
                if (request.getProgressCallback() == null) {
                    internalRequest.setContent(this.wrapRestartableInputStream(input));
                } else {
                    internalRequest.setContent(this.wrapRestartableInputStream(input, request.getProgressCallback()));
                }
            }
        }

        if (request.getProgressCallback() != null && request.getProgressCallback().getTotalSize() <= 0L) {
            request.getProgressCallback().setCurrentSize(0L);
            request.getProgressCallback().setTotalSize(metadata.getContentLength());
        }
        if (request.getTrafficLimitBitPS() > 0) {
            internalRequest.addHeader(HEADER_TRAFFIC_LIMIT, String.valueOf(request.getTrafficLimitBitPS()));
        }
        // Always set the content type from upload url, even if it's already set
        metadata.setContentType(Mimetypes.getInstance().getMimetype(fileName));
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(metadata.getContentLength()));
        populateRequestMetadata(internalRequest, metadata);

        try {
            this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
        } finally {
            try {
                internalRequest.getContent().close();
            } catch (Exception e) {
                logger.warn("Fail to close input stream", e);
            }
        }
    }

    public List<String> uploadLocalFilePart(FileUploadRequest request, int numParts, List<String> urls) {
        checkNotNull(request.getFile(), "file should be set when using multipart upload.");
        if (CollectionUtils.isNotEmpty(request.getMultipartProgressCallbacks())) {
            checkArgument(request.getMultipartProgressCallbacks().size() == numParts, "if using progressCallbacks, the size of progressCallbacks should be equal to numParts.");
        }
        checkArgument(request.getMultipartUploadParallelism() <= numParts,
                "multipartUploadParallelism should be less than or equal to numParts when using concurrent multipart uploading");

        File file = request.getFile();
        long fileSize = file.length();
        long partSize = fileSize / numParts;

        if (request.getMultipartUploadParallelism() > 1) {
            // parallel upload
            return uploadPartsInParallel(request, numParts, urls, partSize);
        } else {
            // serial upload
            return uploadPartsInSeries(request, numParts, urls, partSize);
        }
    }

    private List<String> uploadPartsInSeries(FileUploadRequest request, int numParts, List<String> urls, long partSize) {
        List<String> etags = new ArrayList<>();
        for (int i = 0; i < numParts; i++) {
            etags.add(uploadPart(request, partSize, i, urls.get(i)));
        }
        return etags;
    }

    private List<String> uploadPartsInParallel(FileUploadRequest request, int numParts, List<String> urls, long partSize) {
        ExecutorService executor = new ThreadPoolExecutor(request.getMultipartUploadParallelism(), request.getMultipartUploadParallelism(), 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(numParts));
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (int i = 0; i < numParts; i++) {
            final int partIndex = i;
            futures.add(CompletableFuture.supplyAsync(() -> uploadPart(request, partSize, partIndex, urls.get(partIndex)), executor).exceptionally(ex -> {
                throw new BceClientException("Error during upload: " + partIndex, ex);
            }));
        }

        // wait all part completed
        List<String> etags = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        executor.shutdown();
        try {
            if (!executor.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new BceClientException("close thread pool fail exception", e);
        }
        return etags;
    }

    private String uploadPart(FileUploadRequest request, long partSize, int partIndex, String uploadUrl) {
        URI uri;
        try {
            uri = new URI(uploadUrl);
        } catch (URISyntaxException e) {
            throw new BceClientException("Unable to parse uploadUrl", e);
        }

        File file = request.getFile();
        try (FileInputStream fis = new FileInputStream(file)) {
            long skipBytes = partSize * partIndex;
            fis.skip(skipBytes);

            long size = Math.min(partSize, file.length() - skipBytes);
            if (size > 5 * 1024 * 1024 * 1024L) {
                throw new BceClientException("Part Size should not be more than 5GB.");
            }

            InternalRequest internalRequest = new InternalRequest(HttpMethodName.PUT, uri);
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(size));
            if (request.getTrafficLimitBitPS() > 0) {
                internalRequest.addHeader(HEADER_TRAFFIC_LIMIT, String.valueOf(request.getTrafficLimitBitPS()));
            }
            if (CollectionUtils.isNotEmpty(request.getMultipartProgressCallbacks())) {
                VodUploadProgressCallback<?> progressCallback = request.getMultipartProgressCallbacks().get(partIndex);
                if (progressCallback.getTotalSize() <= 0L) {
                    progressCallback.setCurrentSize(0L);
                    progressCallback.setTotalSize(size);
                }
                internalRequest.setContent(this.wrapRestartableInputStream(fis, size, progressCallback));
            } else {
                internalRequest.setContent(this.wrapRestartableInputStream(fis, size));
            }

            AbstractBceResponse response = this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
            return response.getMetadata().getETag();
        } catch (IOException e) {
            throw new BceClientException("Unable to open file", e);
        }
    }

    public GenericTaskResponse fetchMedia(MediaFetchRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, MEDIAS, FETCH);
        return this.invokeHttpClient(internalRequest, GenericTaskResponse.class);
    }

    public MediaDetail getMedia(String mediaId) {
        GenericMediaRequest mediaGetRequest = new GenericMediaRequest().withMediaId(mediaId);
        InternalRequest request = this.createRequest(mediaGetRequest, HttpMethodName.GET, MEDIAS, mediaGetRequest.getMediaId());
        return this.invokeHttpClient(request, MediaDetail.class);
    }

    public ListByMarkerResponse<MediaBasicInfo> listMedia(MediaListRequest searchRequest) {
        InternalRequest request = this.createRequest(searchRequest, HttpMethodName.GET, MEDIAS);
        request.addParameter(MAX_SIZE, String.valueOf(searchRequest.getMaxSize()));
        if (StringUtils.isNotBlank(searchRequest.getMarker())) {
            request.addParameter(MARKER, searchRequest.getMarker());
        }
        if (StringUtils.isNotBlank(searchRequest.getName())) {
            request.addParameter("name", searchRequest.getName());
        }
        if (StringUtils.isNotBlank(searchRequest.getMediaId())) {
            request.addParameter("mediaId", searchRequest.getMediaId());
        }
        if (StringUtils.isNotBlank(searchRequest.getBanStatus())) {
            request.addParameter("banStatus", searchRequest.getBanStatus());
        }
        if (StringUtils.isNotBlank(searchRequest.getSourceType())) {
            request.addParameter("sourceType", searchRequest.getSourceType());
        }
        if (CollectionUtils.isNotEmpty(searchRequest.getMediaTypes())) {
            request.addParameter("mediaTypes", StringUtils.join(searchRequest.getMediaTypes(), ","));
        }
        if (CollectionUtils.isNotEmpty(searchRequest.getCategoryIds())) {
            request.addParameter("categoryIds", StringUtils.join(searchRequest.getCategoryIds(), ","));
        }
        if (CollectionUtils.isNotEmpty(searchRequest.getTags())) {
            request.addParameter("tags", StringUtils.join(searchRequest.getTags(), ","));
        }
        if (StringUtils.isNotBlank(searchRequest.getBeginTime())) {
            request.addParameter(BEGIN_TIME, searchRequest.getBeginTime());
        }
        if (StringUtils.isNotBlank(searchRequest.getEndTime())) {
            request.addParameter(END_TIME, searchRequest.getEndTime());
        }
        return this.invokeHttpClient(request, ListByMarkerResponse.class);
    }

    public void updateMedia(String mediaId, MediaUpdateRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, MEDIAS, mediaId);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteMediaOutput(String mediaId, MediaOutputDeleteRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, MEDIAS, mediaId, OUTPUT_DELETE);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteMedia(String mediaId) {
        GenericMediaRequest request = new GenericMediaRequest().withMediaId(mediaId);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, MEDIAS, mediaId);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void banMedia(String mediaId) {
        GenericMediaRequest mediaBanRequest = new GenericMediaRequest().withMediaId(mediaId);
        InternalRequest internalRequest = this.createRequest(mediaBanRequest, HttpMethodName.PUT, MEDIAS, BAN);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void unBanMedia(String mediaId) {
        GenericMediaRequest mediaUnBanRequest = new GenericMediaRequest().withMediaId(mediaId);
        InternalRequest internalRequest = this.createRequest(mediaUnBanRequest, HttpMethodName.PUT, MEDIAS, UNBAN);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GenericTaskResponse processMedia(MediaProcessRequest mediaProcessRequest) {
        InternalRequest request = this.createRequest(mediaProcessRequest, HttpMethodName.POST, MEDIAS, PROCESS);
        return this.invokeHttpClient(request, GenericTaskResponse.class);
    }

    public GenericTaskResponse composeMedia(ComposeCreateRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, MEDIA_COMPOSE);
        return this.invokeHttpClient(internalRequest, GenericTaskResponse.class);
    }

    public GenericTaskResponse dwmDetect(MediaDwmDetectRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, MEDIA_DWM_DETECT);
        return this.invokeHttpClient(internalRequest, GenericTaskResponse.class);
    }

    public ListByMarkerResponse<MediaTaskBasicInfo> listMediaTask(MediaTaskListRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, TASKS);
        internalRequest.addParameter("maxSize", String.valueOf(request.getMaxSize()));
        if (StringUtils.isNotBlank(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (StringUtils.isNotBlank(request.getMediaId())) {
            internalRequest.addParameter("mediaId", request.getMediaId());
        }
        if (StringUtils.isNotBlank(request.getTaskId())) {
            internalRequest.addParameter("taskId", request.getTaskId());
        }
        if (StringUtils.isNotBlank(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (StringUtils.isNotBlank(request.getBeginTime())) {
            internalRequest.addParameter(BEGIN_TIME, request.getBeginTime());
        }
        if (StringUtils.isNotBlank(request.getEndTime())) {
            internalRequest.addParameter(END_TIME, request.getEndTime());
        }
        return this.invokeHttpClient(internalRequest, ListByMarkerResponse.class);
    }

    public MediaTaskDetail getMediaTask(String taskId) {
        GenericTaskRequest mediaTaskGetRequest = new GenericTaskRequest().withTaskId(taskId);
        InternalRequest internalRequest = this.createRequest(mediaTaskGetRequest, HttpMethodName.GET, TASKS, taskId);
        return this.invokeHttpClient(internalRequest, MediaTaskDetail.class);
    }

}
