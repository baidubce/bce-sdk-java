/*
 * Copyright 2014 Baidu, Inc.
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
import com.baidubce.services.vod.model.InternalCreateMediaRequest;
import com.baidubce.services.vod.model.InternalCreateMediaResponse;
import com.baidubce.services.vod.model.ListMediaResourceRequest;
import com.baidubce.services.vod.model.ListMediaResourceResponse;
import com.baidubce.services.vod.model.ListMediaResourceByMarkerRequest;
import com.baidubce.services.vod.model.ListMediaResourceByMarkerResponse;
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
import com.baidubce.services.vod.model.GetMediaStatisticRequest;
import com.baidubce.services.vod.model.GetMediaStatisticResponse;
import com.baidubce.services.vod.model.ReTranscodeRequest;
import com.baidubce.services.vod.model.ReTranscodeResponse;
import com.baidubce.services.vod.model.GetMediaSourceDownloadRequest;
import com.baidubce.services.vod.model.GetMediaSourceDownloadResponse;
import com.baidubce.services.vod.model.CreateNotificationRequest;
import com.baidubce.services.vod.model.CreateNotificationResponse;
import com.baidubce.services.vod.model.GetNotificationRequest;
import com.baidubce.services.vod.model.GetNotificationResponse;
import com.baidubce.services.vod.model.ListNotificationsRequest;
import com.baidubce.services.vod.model.ListNotificationsResponse;
import com.baidubce.services.vod.model.DeleteNotificationRequest;
import com.baidubce.services.vod.model.DeleteNotificationResponse;
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

    private static final String PATH_NOTIFICATION = "notification";

    private static final String PATH_INTERNAL_MEDIA = "media/internal";

    /**
     * The URI path for player code service.
     */
    private static final String PATH_SERVICE_CODE = "service/code";

    /**
     * The URI path for playable URL service.
     */
    private static final String PATH_SERVICE_FILE = "service/file";

    /**
     * The URI path for media statistic URL service.
     */
    private static final String MEDIA_STATISTIC = "statistic/media";

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
    private static final String START_TIME = "startTime";
    private static final String END_TIME = "endTime";
    private static final String PARAM_MAX_SIZE = "maxSize";
    private static final String PARAM_MARKER = "marker";
    private static final String PARAM_TRANSCODING_PRESET_NAME = "transcodingPresetName";
    private static final String AGGREGATE = "aggregate";
    private static final String PARA_RERUN = "rerun";
    private static final String PARA_DOWNLOAD = "sourcedownload";
    private static final String PARA_EXPIREDINSECONDS = "expiredInSeconds";
    private static final String VALID_EXTENSION_PATTERN = "[0-9a-zA-Z]{0,10}";
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
     * @param title media titile.
     * @param description media description.
     * @param file The file containing the data to be uploaded to VOD.
     * @param transcodingPresetGroupName set transcoding presetgroup name, if NULL, use default
     * @param priority set transcoding priority[0,9], lowest priority is 0. Only effect your own task
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     * @throws FileNotFoundException
     */
    public CreateMediaResourceResponse createMediaResource(
            String title,
            String description,
            File file,
            String transcodingPresetGroupName,
            int priority)
            throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("The media file " + file.getAbsolutePath() + " doesn't exist!");
        }
        // try get file extension
        String sourceExtension = null;
        String filename = file.getName();
        sourceExtension = getFileExtension(filename);
        // get a BOS bucket and extract mediaId from it
        GenerateMediaIdResponse generateMediaIdresponse = applyMedia();
        String bosKey = generateMediaIdresponse.getSourceKey();
        String mediaId = generateMediaIdresponse.getMediaId();
        String bucket = generateMediaIdresponse.getSourceBucket();

        logger.info("[bucket] = " + bucket
                + ", [bosKey] = " + bosKey
                + ", [mediaId] = " + mediaId);
        // upload the file
        FileUploadSession session = new FileUploadSession(bosClient);
        CreateMediaResourceResponse response = new CreateMediaResourceResponse();

        if (session.upload(file, bucket, bosKey)) {
            InternalCreateMediaRequest request =
                    new InternalCreateMediaRequest()
                            .withMediaId(mediaId)
                            .withTitle(title)
                            .withDescription(description)
                            .withSourceExtension(sourceExtension)
                            .withTranscodingPresetGroupName(transcodingPresetGroupName)
                            .withPriority(priority);
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
     * @param transcodingPresetGroupName set transcoding presetgroup name, if NULL, use default
     * @param priority set transcoding priority[0,9], lowest priority is 0. Only effect your own jobs
     * @return A PutObjectResponse object containing the information returned by Bos for the newly created object.
     */
    public CreateMediaResourceResponse createMediaResource(
            String sourceBucket,
            String sourceKey,
            String title,
            String description,
            String transcodingPresetGroupName,
            int priority) {
        checkStringNotEmpty(sourceBucket, "sourceBucket should not be null or empty!");
        checkStringNotEmpty(sourceKey, "key should not be null or empty!");

        // check if the key exist
        ObjectMetadata metaData = bosClient.getObjectMetadata(sourceBucket, sourceKey);
        checkIsTrue(metaData != null && metaData.getContentLength() > 0,
                "The object corresponding to [bucket] = " 
                + sourceBucket + ", [key] = " + sourceKey + " doesn't exist.");

        // try get file extension
        String sourceExtension = null;
        sourceExtension = getFileExtension(sourceKey);
        // generate media Id
        GenerateMediaIdResponse generateMediaIdresponse = applyMedia();
        String mediaId = generateMediaIdresponse.getMediaId();
        String targetBucket = generateMediaIdresponse.getSourceBucket();
        String targetKey = generateMediaIdresponse.getSourceKey();

        // copy to temp bucket
        bosClient.copyObject(sourceBucket, sourceKey, targetBucket, targetKey);

        // create mediaId
        InternalCreateMediaRequest request =
                new InternalCreateMediaRequest()
                        .withMediaId(mediaId)
                        .withTitle(title)
                        .withDescription(description)
                        .withSourceExtension(sourceExtension)
                        .withTranscodingPresetGroupName(transcodingPresetGroupName)
                        .withPriority(priority);
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
     * recommend use marker mode to get high performance
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param pageNo The pageNo need to list, must be greater than 0
     * @param pageSize The pageSize ,must in range [LIST_MIN_PAGESIZE,LIST_MAX_PAGESIZE]
     * @param status The media status, can be null
     * @param begin The media create date after begin
     * @param end The media create date before end
     * @param title The media title, use prefix search
     * @return The properties of all specific media resources
     */
    public ListMediaResourceResponse listMediaResources(
            int pageNo,
            int pageSize,
            String status,
            Date begin,
            Date end,
            String title) {
        ListMediaResourceRequest request =
                new ListMediaResourceRequest()
                        .withPageNo(pageNo)
                        .withPageSize(pageSize)
                        .withStatus(status)
                        .withBegin(begin)
                        .withEnd(end)
                        .withTitle(title);

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
     * Use marker mode to List the properties of all media resource managed by VOD service.
     * If media size beyond 1000, strongly recommend to use marker mode
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param marker The marker labels the query begining; first query use NULL.
     * @param maxSize The maxSize returned ,must in range [LIST_MIN_PAGESIZE,LIST_MAX_PAGESIZE]
     * @param status The media status, can be null
     * @param begin The media create date after begin
     * @param end The media create date before end
     * @param title The media title, use prefix search
     * @return The properties of all specific media resources
     */
    public ListMediaResourceByMarkerResponse listMediaResourcesByMarker(
            String marker,
            int maxSize,
            String status,
            Date begin,
            Date end,
            String title) {
        ListMediaResourceByMarkerRequest request =
                new ListMediaResourceByMarkerRequest().withMarker(marker).withMaxSize(maxSize)
                        .withStatus(status).withBegin(begin).withEnd(end).withTitle(title);

        return listMediaResourcesByMarker(request);
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
    public ListMediaResourceByMarkerResponse listMediaResourcesByMarker(ListMediaResourceByMarkerRequest request) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PATH_MEDIA);
        internalRequest.addParameter(PARAM_MAX_SIZE, String.valueOf(request.getMaxSize()));
        if (request.getMarker() != null) {
            internalRequest.addParameter(PARAM_MARKER, String.valueOf(request.getMarker()));
        }
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

        return invokeHttpClient(internalRequest, ListMediaResourceByMarkerResponse.class);
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
     * Generate media delivery info by media ID.
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param mediaId The unique ID for each media resource
     * @return media delivery info
     */
    public GenerateMediaDeliveryInfoResponse generateMediaDeliveryInfo(String mediaId, String transcodingPresetName) {
        GenerateMediaDeliveryInfoRequest request = new GenerateMediaDeliveryInfoRequest()
                .withMediaId(mediaId)
                .withTranscodingPresetName(transcodingPresetName);
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
        internalRequest.addParameter(PARAM_TRANSCODING_PRESET_NAME, request.getTranscodingPresetName());
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
        int height, boolean autoStart,
        String transcodingPresetName) {
        GenerateMediaPlayerCodeRequest request = new GenerateMediaPlayerCodeRequest()
                .withMediaId(mediaId).withWidth(width)
                .withHeight(height).withAutoStart(autoStart)
                .withTranscodingPresetName(transcodingPresetName);
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
        internalRequest.addParameter(PARAM_TRANSCODING_PRESET_NAME, request.getTranscodingPresetName());

        return invokeHttpClient(internalRequest, GenerateMediaPlayerCodeResponse.class);
    }

    /**
     * get media statistic info.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param startTime, query media start time, default:2016-04-30T16:00:00Z
     * @param endTime, query media end time, default:now
     * @param aggregate, if need aggregate, default: true
     * @return The media statistic info
     */
    public GetMediaStatisticResponse getMediaStatistic(
            String mediaId,
            Date startTime,
            Date endTime,
            boolean aggregate) {
        GetMediaStatisticRequest request =
                new GetMediaStatisticRequest().withMediaId(mediaId)
                        .withStartTime(startTime).withEndTime(endTime)
                        .withAggregate(aggregate);

        return getMediaStatistic(request);
    }

    /**
     * get media statistic info.
     *
     * <p>
     * The caller <i>must</i> authenticate with a valid BCE Access Key / Private Key pair.
     *
     * @param request The request wrapper object containing all options.
     * @return The media statistic info
     */
    public GetMediaStatisticResponse getMediaStatistic(GetMediaStatisticRequest request) {
        checkIsTrue(request.getMediaId() != null, "mediaId is null!");

        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, MEDIA_STATISTIC, request.getMediaId());
        if (request.getStartTime() != null) {
            internalRequest.addParameter(START_TIME,
                    DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter(END_TIME,
                    DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        internalRequest.addParameter(AGGREGATE, String.valueOf(request.isAggregate()));
        return invokeHttpClient(internalRequest, GetMediaStatisticResponse.class);
    }

    /**
     * Transcode the media again. Only status is FAILED or PUBLISHED media can use.
     *
     * @param mediaId The unique ID for each media resource
     * @return
     */
    public ReTranscodeResponse reTranscode(String mediaId) {
        ReTranscodeRequest request = new ReTranscodeRequest().withMediaId(mediaId);
        return reTranscode(request);
    }

    /**
     * Transcode the media again. Only status is FAILED or PUBLISHED media can use.
     *
     * @param request The request object containing mediaid
     * @return
     */
    public ReTranscodeResponse reTranscode(ReTranscodeRequest request) {

        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");

        InternalRequest internalRequest =
                createRequest(HttpMethodName.PUT, request, PATH_MEDIA, request.getMediaId());
        internalRequest.addParameter(PARA_RERUN, null);

        return invokeHttpClient(internalRequest, ReTranscodeResponse.class);
    }

    /**
     * get media source download url.
     *
     * @param mediaId The unique ID for each media resource
     * @param expiredInSeconds The expire time
     * @return
     */
    public GetMediaSourceDownloadResponse getMediaSourceDownload(String mediaId, long expiredInSeconds) {
        GetMediaSourceDownloadRequest request = new GetMediaSourceDownloadRequest()
                .withMediaId(mediaId)
                .withExpiredInSeconds(expiredInSeconds);
        return getMediaSourceDownload(request);
    }

    /**
     * Transcode the media again. Only status is FAILED or PUBLISHED media can use.
     *
     * @param request The request object containing mediaid
     * @return
     */
    public GetMediaSourceDownloadResponse getMediaSourceDownload(GetMediaSourceDownloadRequest request) {

        checkStringNotEmpty(request.getMediaId(), "Media ID should not be null or empty!");

        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PATH_MEDIA, request.getMediaId());
        internalRequest.addParameter(PARA_DOWNLOAD, null);
        internalRequest.addParameter(PARA_EXPIREDINSECONDS, String.valueOf(request.getExpiredInSeconds()));

        return invokeHttpClient(internalRequest, GetMediaSourceDownloadResponse.class);
    }

    /**
     * List all your doc notifications.
     *
     * @return The list of all your doc notifications
     */
    public ListNotificationsResponse listNotifications() {
        ListNotificationsRequest request = new ListNotificationsRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PATH_NOTIFICATION);
        return invokeHttpClient(internalRequest, ListNotificationsResponse.class);
    }

    /**
     * Delete your doc notification by doc notification name.
     *
     * @param name  doc notification name.
     *
     */
    public DeleteNotificationResponse deleteNotification(String name) {
        DeleteNotificationRequest request = new DeleteNotificationRequest();
        request.setName(name);
        return deleteNotification(request);
    }

    /**
     * Delete your doc notification by doc notification name.
     *
     * @param request The request object containing all parameters for deleting dco notification.
     *
     */
    public DeleteNotificationResponse deleteNotification(DeleteNotificationRequest request) {
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE, request, PATH_NOTIFICATION, request
                .getName());
        return invokeHttpClient(internalRequest, DeleteNotificationResponse.class);
    }

    /**
     * Get your doc notification by doc notification name.
     *
     * @param name  doc notification name.
     *
     * @return Your doc notification.
     */
    public GetNotificationResponse getNotification(String name) {
        GetNotificationRequest request = new GetNotificationRequest();
        request.setName(name);
        return getNotification(request);
    }

    /**
     * Get your doc notification by doc notification name.
     *
     * @param request The request object containing all parameters for getting doc notification.
     *
     * @return Your doc notification.
     */
    public GetNotificationResponse getNotification(GetNotificationRequest request) {
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PATH_NOTIFICATION,
                request.getName());
        return invokeHttpClient(internalRequest, GetNotificationResponse.class);
    }

    /**
     * Create a doc notification in the doc stream service.
     *
     * @param name  The name of notification.
     * @param endpoint The address to receive notification message.
     *
     */
    public CreateNotificationResponse createNotification(String name, String endpoint) {
        CreateNotificationRequest request = new CreateNotificationRequest();
        request.withName(name).withEndpoint(endpoint);
        return createNotification(request);
    }

    /**
     * Create a doc notification in the doc stream service.
     *
     * @param request The request object containing all options for creating doc notification.
     */
    public CreateNotificationResponse createNotification(CreateNotificationRequest request) {

        checkStringNotEmpty(request.getName(),
                "The parameter name should NOT be null or empty string.");
        checkStringNotEmpty(request.getEndpoint(),
                "The parameter endpoint should NOT be null or empty string.");

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, PATH_NOTIFICATION);
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
        return invokeHttpClient(internalRequest, CreateNotificationResponse.class);
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


    private String getFileExtension(String filename) {
        if (filename != null && filename.lastIndexOf(".") != -1) {
            String extension = filename.substring(filename.lastIndexOf(".") + 1);
            if (extension.length() <= 0 || extension.length() > MAX_SOURCE_EXTENSION_LENGTH) {
                return null;
            }
            if (!extension.matches(VALID_EXTENSION_PATTERN)) {
                return null;
            }
            return extension;
        }
        return null;
    }

    /*
     * VOD is global service, doesn't support region.
     */
    @Override
    public boolean isRegionSupported() {
        return false;
    }

}
