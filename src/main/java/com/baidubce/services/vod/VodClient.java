/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.vod;

import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkStringNotEmpty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baidubce.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.Region;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.vod.model.CreateMediaResourceResponse;
import com.baidubce.services.vod.model.DeleteMediaResourceRequest;
import com.baidubce.services.vod.model.DeleteMediaResourceResponse;
import com.baidubce.services.vod.model.GenerateMediaIdRequest;
import com.baidubce.services.vod.model.GenerateMediaIdResponse;
import com.baidubce.services.vod.model.GetMediaResourceRequest;
import com.baidubce.services.vod.model.GetMediaResourceResponse;
import com.baidubce.services.vod.model.GetPlayableUrlRequest;
import com.baidubce.services.vod.model.GetPlayableUrlResponse;
import com.baidubce.services.vod.model.GetPlayerCodeRequest;
import com.baidubce.services.vod.model.GetPlayerCodeResponse;
import com.baidubce.services.vod.model.InternalCreateMediaRequest;
import com.baidubce.services.vod.model.InternalCreateMediaResponse;
import com.baidubce.services.vod.model.ListMediaResourceRequest;
import com.baidubce.services.vod.model.ListMediaResourceResponse;
import com.baidubce.services.vod.model.PublishMediaResourceRequest;
import com.baidubce.services.vod.model.PublishMediaResourceResponse;
import com.baidubce.services.vod.model.StopMediaResourceRequest;
import com.baidubce.services.vod.model.StopMediaResourceResponse;
import com.baidubce.services.vod.model.UpdateMediaResourceRequest;
import com.baidubce.services.vod.model.UpdateMediaResourceResponse;
import com.baidubce.services.vod.model.GenerateMediaDeliveryInfoRequest;
import com.baidubce.services.vod.model.GenerateMediaDeliveryInfoResponse;
import com.baidubce.services.vod.model.GenerateMediaPlayerCodeRequest;
import com.baidubce.services.vod.model.GenerateMediaPlayerCodeResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Provides the client for accessing the Baidu MediaResource-On-Demand Service.
 */
public class VodClient extends AbstractBceClient {
    /**
     * The version information for VOD service APIs as URI prefix.
     */
    private static final String VERSION = "v1";

    /**
     * The URI path for player code service.
     */
    private static final String PATH_MEDIA = "media";

    private static final String PATH_INTERNAL_MEDIA = "media/internal";

    /**
     * The URI path for player code service.
     */
    private static final String PATH_SERVICE_CODE = "service/code";

    /**
     * The URI path for playable URL service.
     */
    private static final String PATH_SERVICE_FILE = "service/file";

    private static final String PARA_PUBLISH = "publish";
    private static final String PARA_DISABLE = "disable";
    private static final String PARA_MEDIA_ID = "media_id";
    private static final String PARA_WIDTH = "width";
    private static final String PARA_HEIGHT = "height";
    private static final String PARA_AUTO_START = "auto_start";
    private static final String PARA_AUTO_START_NEW = "autostart";
    private static final String PARA_ATTRIBUTES = "attributes";
    private static final String PARA_APPLY = "apply";
    private static final String PARA_PROCESS = "process";
    private static final String PARA_GENDELIVERY = "delivery";
    private static final String PARA_GENCODE = "code";
    private static final String PARA_AK = "ak";
    private static final String PARA_PAGENO = "pageNo";
    private static final String PARA_PAGESIZE = "pageSize";
    private static final String PARA_STATUS = "status";
    private static final String PARA_BEGIN = "begin";
    private static final String PARA_END = "end";
    private static final String PARA_TITLE = "title";
    private static final int LIST_MAX_PAGESIZE = 1000;
    private static final int LIST_MIN_PAGESIZE = 1;
    private static final int MAX_SOURCE_EXTENSION_LENGTH = 10;

    private static Logger logger = LoggerFactory.getLogger(VodClient.class);

    private BosClient bosClient;

    /**
     * Responsible for handling httpResponses from all Bos service calls.
     */
    private static final HttpResponseHandler[] responseHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client using the client configuration.
     *
     * @param vodConfig The client configuration options controlling how this client connects to Vod services
     *            (e.g. proxy settings, retry counts, etc).
     */
    public VodClient(BceClientConfiguration vodConfig) {
        // enable HTTP Async manner for PUT method
        super(vodConfig, responseHandlers, true);

        BosClientConfiguration bosConfig = new BosClientConfiguration(vodConfig, null).withRegion(Region.CN_N1);
        bosClient = new BosClient(bosConfig);

    }

    /**
     * Constructs a new client using the client configuration. Used for test only
     *
     * @param vodConfig The client configuration options controlling how this client connects to Vod services
     *            (e.g. proxy settings, retry counts, etc).
     */
    VodClient(BceClientConfiguration vodConfig, BosClientConfiguration bosConfig) {
        // enable HTTP Async manner for PUT method
        super(vodConfig, responseHandlers, true);
        bosClient = new BosClient(bosConfig);

    }

    /**
     * Uploads the specified file to Bos under the specified bucket and key name.
     *
     * @param file The file containing the data to be uploaded to VOD
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     * @throws FileNotFoundException
     */
    public CreateMediaResourceResponse createMediaResource(String title, String description, File file)
            throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("The media file " + file.getAbsolutePath() + " doesn't exist!");
        }
        // try get file extension
        String sourceExtension = null;
        String filename = file.getName();
        if (filename.lastIndexOf(".") != -1) {
            sourceExtension = filename.substring(filename.lastIndexOf(".") + 1);
            if (sourceExtension.length() <= 0 || sourceExtension.length() > MAX_SOURCE_EXTENSION_LENGTH) {
                sourceExtension = null;
            }
        }
        // get a BOS bucket and extract mediaId from it
        GenerateMediaIdResponse generateMediaIdresponse = applyMedia();
        String bosKey = generateMediaIdresponse.getSourceKey();
        String mediaId = generateMediaIdresponse.getMediaId();
        String bucket = generateMediaIdresponse.getSourceBucket();

        logger.info("[bucket] = " + bucket + ", [bosKey] = " + bosKey + ", [mediaId] = " + mediaId);
        // upload the file
        FileUploadSession session = new FileUploadSession(bosClient);
        CreateMediaResourceResponse response = new CreateMediaResourceResponse();

        if (session.upload(file, bucket, bosKey)) {
            InternalCreateMediaRequest request =
                    new InternalCreateMediaRequest().withMediaId(mediaId).withTitle(title)
                            .withDescription(description).withSourceExtension(sourceExtension);
            InternalCreateMediaResponse internalResponse = processMedia(request);
            response.setMediaId(internalResponse.getMediaId());
        }

        // create media resource with mediaId, title, description
        return response;
    }

    /**
     * Load a media resource from BOS to VOD.
     *
     * @param sourceBucket The bucket name of the media resource in BOS
     * @param sourceKey The key name of the media resource in BOS
     * @param title The title string of the media resource
     * @param description The description string of the media resource
     * 
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     * @throws FileNotFoundException
     */
    public CreateMediaResourceResponse createMediaResource(
            String sourceBucket, String sourceKey, String title, String description) {
        checkStringNotEmpty(sourceBucket, "sourceBucket should not be null or empty!");
        checkStringNotEmpty(sourceKey, "key should not be null or empty!");

        // check if the key exist
        ObjectMetadata metaData = bosClient.getObjectMetadata(sourceBucket, sourceKey);
        checkIsTrue(metaData != null && metaData.getContentLength() > 0,
               "The object corresponding to [bucket] = " + sourceBucket + ", [key] = " + sourceKey + " doesn't exist.");

        // try get file extension
        String sourceExtension = null;
        if (sourceKey.lastIndexOf(".") != -1) {
            sourceExtension = sourceKey.substring(sourceKey.lastIndexOf(".") + 1);
            if (sourceExtension.length() <= 0 || sourceExtension.length() > MAX_SOURCE_EXTENSION_LENGTH) {
                sourceExtension = null;
            }
        }
        // generate media Id
        GenerateMediaIdResponse generateMediaIdresponse = applyMedia();
        String mediaId = generateMediaIdresponse.getMediaId();
        String targetBucket = generateMediaIdresponse.getSourceBucket();
        String targetKey = generateMediaIdresponse.getSourceKey();

        // copy to temp bucket
        bosClient.copyObject(sourceBucket, sourceKey, targetBucket, targetKey);

        // create mediaId
        InternalCreateMediaRequest request =
                new InternalCreateMediaRequest().withMediaId(mediaId).withTitle(title)
                        .withDescription(description).withSourceExtension(sourceExtension);
        InternalCreateMediaResponse internalResponse = processMedia(request);

        CreateMediaResourceResponse response = new CreateMediaResourceResponse();
        response.setMediaId(internalResponse.getMediaId());

        return response;
    }

    public InternalCreateMediaResponse processMedia(InternalCreateMediaRequest request) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.PUT, request, PATH_MEDIA, request.getMediaId());
        internalRequest.addParameter(PARA_PROCESS, null);

        return invokeHttpClient(internalRequest, InternalCreateMediaResponse.class);
    }

    public GenerateMediaIdResponse applyMedia() {
        GenerateMediaIdRequest request = new GenerateMediaIdRequest();
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, PATH_MEDIA);
        internalRequest.addParameter(PARA_APPLY, null);

        return invokeHttpClient(internalRequest, GenerateMediaIdResponse.class);
    }

    /**
     * Gets the properties of specific media resource managed by VOD service.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param mediaId The unique ID for each media resource
     * @return The properties of the specific media resource
     */
    public GetMediaResourceResponse getMediaResource(String mediaId) {
        GetMediaResourceRequest request = new GetMediaResourceRequest().withMediaId(mediaId);
        return getMediaResource(request);
    }

    /**
     * Gets the properties of specific media resource managed by VOD service.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request wrapper object containing all options.
     * @return The properties of the specific media resource
     */
    public GetMediaResourceResponse getMediaResource(GetMediaResourceRequest request) {
        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PATH_MEDIA, request.getMediaId());

        return invokeHttpClient(internalRequest, GetMediaResourceResponse.class);
    }

    /**
     * List the properties of all media resource managed by VOD service.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @return The properties of all specific media resources
     */
    @Deprecated
    public ListMediaResourceResponse listMediaResources() {
        ListMediaResourceRequest request = new ListMediaResourceRequest();
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PATH_MEDIA);

        return invokeHttpClient(internalRequest, ListMediaResourceResponse.class);
    }

    /**
     * List the properties of all media resource managed by VOD service.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param pageNo The pageNo need to list, must >0
     * @param pageSize The pageSize ,must in range [LIST_MIN_PAGESIZE,LIST_MAX_PAGESIZE]
     * @pagam status The media status, can be null
     * @param begin The media create date after begin
     * @param end The media create date before end
     * @param title The media title, use prefix search
     * @return The properties of all specific media resources
     */
    public ListMediaResourceResponse listMediaResources(int pageNo, int pageSize, String status,
                                                        Date begin, Date end, String title) {
        ListMediaResourceRequest request =
                new ListMediaResourceRequest().withPageNo(pageNo).withPageSize(pageSize)
                .withStatus(status).withBegin(begin).withEnd(end).withTitle(title);

        return listMediaResources(request);
    }

    /**
     * List the properties of all media resource managed by VOD service.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request wrapper object containing all options.
     * @return The properties of all specific media resources
     */
    public ListMediaResourceResponse listMediaResources(ListMediaResourceRequest request) {
        checkIsTrue(request.getPageNo() > 0, "pageNo should greater than 0!");
        checkIsTrue(request.getPageSize() >= LIST_MIN_PAGESIZE
                && request.getPageSize() <= LIST_MAX_PAGESIZE, "pageSize is not Valid!");

        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PATH_MEDIA);
        internalRequest.addParameter(PARA_PAGENO, String.valueOf(request.getPageNo()));
        internalRequest.addParameter(PARA_PAGESIZE, String.valueOf(request.getPageSize()));
        if (request.getStatus() != null) {
            internalRequest.addParameter(PARA_STATUS, request.getStatus());
        }
        if (request.getBegin() != null) {
            internalRequest.addParameter(PARA_BEGIN, DateUtils.formatAlternateIso8601Date(request.getBegin()));
        }
        if (request.getEnd() != null) {
            internalRequest.addParameter(PARA_END, DateUtils.formatAlternateIso8601Date(request.getEnd()));
        }
        if (request.getTitle() != null) {
            internalRequest.addParameter(PARA_TITLE, request.getTitle());
        }

        return invokeHttpClient(internalRequest, ListMediaResourceResponse.class);
    }

    /**
     * Update the title and description for the specific media resource managed by VOD service.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param mediaId The unique ID for each media resource
     * @param title New title string
     * @param description New description string
     * @return empty response will be returned
     */
    public UpdateMediaResourceResponse updateMediaResource(String mediaId, String title, String description) {
        UpdateMediaResourceRequest request =
                new UpdateMediaResourceRequest().withMediaId(mediaId).withTitle(title).withDescription(description);

        return updateMediaResource(request);
    }

    /**
     * Update the title and description for the specific media resource managed by VOD service.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request wrapper object containing all options.
     * @return empty response will be returned
     */
    public UpdateMediaResourceResponse updateMediaResource(UpdateMediaResourceRequest request) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.PUT, request, PATH_MEDIA, request.getMediaId());
        internalRequest.addParameter(PARA_ATTRIBUTES, null);

        return invokeHttpClient(internalRequest, UpdateMediaResourceResponse.class);
    }

    /**
     * Stop the specific media resource managed by VOD service, so that it can not be access and played. Disabled media
     * resource can be recovered by method <code>publishMediaResource()</code> later.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param mediaId The unique ID for each media resource
     * @return empty response will be returned
     */
    public StopMediaResourceResponse stopMediaResource(String mediaId) {
        StopMediaResourceRequest request = new StopMediaResourceRequest().withMediaId(mediaId);
        return stopMediaResource(request);
    }

    /**
     * Stop the specific media resource managed by VOD service, so that it can not be access and played. Disabled media
     * resource can be recovered by method <code>publishMediaResource()</code> later.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request object containing all the options on how to
     * @return empty response will be returned
     */
    public StopMediaResourceResponse stopMediaResource(StopMediaResourceRequest request) {
        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.PUT, request, PATH_MEDIA, request.getMediaId());
        internalRequest.addParameter(PARA_DISABLE, null);

        return invokeHttpClient(internalRequest, StopMediaResourceResponse.class);
    }

    /**
     * Publish the specific media resource managed by VOD service, so that it can be access and played.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param mediaId The unique ID for each media resource
     * @return empty response will be returned
     */
    public PublishMediaResourceResponse publishMediaResource(String mediaId) {
        PublishMediaResourceRequest request = new PublishMediaResourceRequest().withMediaId(mediaId);
        return publishMediaResource(request);
    }

    /**
     * Publish the specific media resource managed by VOD service, so that it can be access and played.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request object containing all the options on how to
     * @return empty response will be returned
     */
    public PublishMediaResourceResponse publishMediaResource(PublishMediaResourceRequest request) {
        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.PUT, request, PATH_MEDIA, request.getMediaId());
        internalRequest.addParameter(PARA_PUBLISH, null);

        return invokeHttpClient(internalRequest, PublishMediaResourceResponse.class);
    }

    /**
     * Delete the specific media resource managed by VOD service.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param mediaId The unique ID for each media resource
     * @return empty response will be returned
     */
    public DeleteMediaResourceResponse deleteMediaResource(String mediaId) {
        DeleteMediaResourceRequest request = new DeleteMediaResourceRequest().withMediaId(mediaId);
        return deleteMediaResource(request);
    }

    /**
     * Delete the specific media resource managed by VOD service.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request object containing all the options on how to
     * @return empty response will be returned
     */
    public DeleteMediaResourceResponse deleteMediaResource(DeleteMediaResourceRequest request) {
        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.DELETE, request, PATH_MEDIA, request.getMediaId());
        return invokeHttpClient(internalRequest, DeleteMediaResourceResponse.class);
    }

    /**
     * Get the playable URL address of specific media resource managed by VOD service.
     *
     * @param mediaId The unique ID for each media resource
     * @return the playable URL address
     */
    @Deprecated
    public GetPlayableUrlResponse getPlayableUrl(String mediaId) {
        GetPlayableUrlRequest request = new GetPlayableUrlRequest().withMediaId(mediaId);
        return getPlayableUrl(request);
    }

    /**
     * Get the playable URL address of specific media resource managed by VOD service.
     *
     * @param request The request object containing all the options on how to
     * @return the playable URL address
     */
    @Deprecated
    public GetPlayableUrlResponse getPlayableUrl(GetPlayableUrlRequest request) {
        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PATH_SERVICE_FILE);
        internalRequest.addParameter(PARA_MEDIA_ID, request.getMediaId());

        return invokeHttpClient(internalRequest, GetPlayableUrlResponse.class);
    }

    /**
     * Get the Flash and HTML5 code snippet (encoded in Base64) to play the specific media resource.
     *
     * @param mediaId The unique ID for each media resource
     * @param width The width of player view
     * @param height The height of player view
     * @param autoStart Indicate whether or not play the media resource automatically when web page is loaded.
     * @return The Flash and HTML5 code snippet
     */
    @Deprecated
    public GetPlayerCodeResponse getPlayerCode(String mediaId, int width, int height, boolean autoStart) {
        GetPlayerCodeRequest request = new GetPlayerCodeRequest()
                .withMediaId(mediaId).withWidth(width).withHeight(height).withAutoStart(autoStart);
        return getPlayerCode(request);
    }

    /**
     * Get the Flash and HTML5 codes snippet (encoded in Base64) to play the specific media resource.
     * 
     * @param request The request object containing all the options on how to
     * @return The Flash and HTML5 code snippet
     */
    @Deprecated
    public GetPlayerCodeResponse getPlayerCode(GetPlayerCodeRequest request) {

        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");
        checkIsTrue(request.getHeight() > 0, "Height of playback view should be positive!");
        checkIsTrue(request.getWidth() > 0, "Width of playback view should be positive!");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PATH_SERVICE_CODE);
        internalRequest.addParameter(PARA_MEDIA_ID, request.getMediaId());
        internalRequest.addParameter(PARA_WIDTH, Integer.toString(request.getWidth()));
        internalRequest.addParameter(PARA_HEIGHT, Integer.toString(request.getHeight()));
        internalRequest.addParameter(PARA_AUTO_START, Boolean.toString(request.isAutoStart()));

        return invokeHttpClient(internalRequest, GetPlayerCodeResponse.class);
    }

    /**
     * Generate media delivery info by media ID.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param mediaId The unique ID for each media resource
     * @return media delivery info
     */
    public GenerateMediaDeliveryInfoResponse generateMediaDeliveryInfo(String mediaId) {
        GenerateMediaDeliveryInfoRequest request = new GenerateMediaDeliveryInfoRequest().withMediaId(mediaId);
        return generateMediaDeliveryInfo(request);
    }

    /**
     * Delete the specific media resource managed by VOD service.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request object containing all the options on how to
     * @return empty response will be returned
     */
    public GenerateMediaDeliveryInfoResponse generateMediaDeliveryInfo(GenerateMediaDeliveryInfoRequest request) {
        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PATH_MEDIA, request.getMediaId(), PARA_GENDELIVERY);
        return invokeHttpClient(internalRequest, GenerateMediaDeliveryInfoResponse.class);
    }

    /**
     * Get the HTML5 code snippet (encoded in Base64) to play the specific media resource.
     *
     * @param mediaId The unique ID for each media resource
     * @param width The width of player view
     * @param height The height of player view
     * @param autoStart Indicate whether or not play the media resource automatically when web page is loaded.
     * @return The Flash and HTML5 code snippet
     */
    public GenerateMediaPlayerCodeResponse generateMediaPlayerCode(String mediaId, int width,
                                                                   int height, boolean autoStart) {
        GenerateMediaPlayerCodeRequest request = new GenerateMediaPlayerCodeRequest()
                .withMediaId(mediaId).withWidth(width).withHeight(height).withAutoStart(autoStart);
        return generateMediaPlayerCode(request);
    }

    /**
     * Get the HTML5 code snippet (encoded in Base64) to play the specific media resource.
     *
     * @param request The request object containing all the options on how to
     * @return The Flash and HTML5 code snippet
     */
    public GenerateMediaPlayerCodeResponse generateMediaPlayerCode(GenerateMediaPlayerCodeRequest request) {

        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");
        checkIsTrue(request.getHeight() > 0, "Height of playback view should be positive!");
        checkIsTrue(request.getWidth() > 0, "Width of playback view should be positive!");

        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PATH_MEDIA, request.getMediaId(), PARA_GENCODE);
        internalRequest.addParameter(PARA_WIDTH, Integer.toString(request.getWidth()));
        internalRequest.addParameter(PARA_HEIGHT, Integer.toString(request.getHeight()));
        internalRequest.addParameter(PARA_AUTO_START_NEW, Boolean.toString(request.isAutoStart()));
        internalRequest.addParameter(PARA_AK, config.getCredentials().getAccessKeyId());

        return invokeHttpClient(internalRequest, GenerateMediaPlayerCodeResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource. This method is responsible for
     * determining HTTP method, URI path, credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.
     * </p>
     * 
     * @param httpMethod The HTTP method to use when sending the request.
     * @param request The original request, as created by the user.
     * @param pathVariables The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate any
     *         additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String...pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(VERSION);

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST
                || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(internalRequest, request);
        }
        return internalRequest;
    }

    private InternalRequest fillRequestPayload(InternalRequest internalRequest, AbstractBceRequest request) {
        String strJson = JsonUtils.toJsonString(request);
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));

        return internalRequest;
    }

    /*
     * VOD is global service, doesn't support region.
     */
    @Override
    public boolean isRegionSupported() {
        return false;
    }

}
