/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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

package com.baidubce.services.mms;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.services.mms.model.SourceAndDescRequest;
import com.baidubce.services.mms.model.InsertVideoResultResponse;
import com.baidubce.services.mms.model.SourceRequest;
import com.baidubce.services.mms.model.MmsBaseResponse;
import com.baidubce.services.mms.model.MmsListLibResponse;
import com.baidubce.services.mms.model.MmsListMediaResponse;
import com.baidubce.services.mms.model.SearchImageResponse;
import com.baidubce.services.mms.model.SearchVideoResponse;
import com.baidubce.services.mms.model.CreateLibRequest;
import com.baidubce.services.mms.model.ListLibRequest;
import com.baidubce.services.mms.model.ListMediaRequest;

/** Provides the client for accessing the BCE MMS service. */
public class MmsClient extends AbstractBceClient {

    private static final String VERSION = "v2";

    private static final String VIDEO_LIB = "videolib";

    private static final String IMAGE_LIB = "imagelib";

    private static final String LIB = "lib";

    private static final String LIST = "list";

    private static final String ITEM = "item";

    private static final String SEARCH_BY_IMAGE = "searchByImage";

    private static final String SEARCH_BY_VIDEO = "searchByVideo";

    private static final String DELETE_VIDEO = "deleteVideo";

    private static final String DELETE_IMAGE = "deleteImage";

    private static final String DELETE_VIDEO_BY_ID = "deleteVideoById";

    private static final String DELETE_IMAGE_BY_ID = "deleteImageById";

    private static final String DELETE_LIB_BY_ID = "deleteLibById";

    private static final String SOURCE = "source";

    private static final String MEDIA_ID = "mediaId";

    private static final String TASK_ID = "taskId";

    private static final String GET_INSERT_RESPONSE_BY_ID = "getInsertResponseById";

    private static final String GET_SEARCH_RESPONSE_BY_TASK_ID = "getSearchResponseByTaskId";

    private static HttpResponseHandler[] mmsHandlers =
        new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
        };

    public MmsClient(BceClientConfiguration config) {
        super(config, mmsHandlers);
    }

    /**
     * create a video lib.
     *
     * @param request The params of create video lib.
     * @return BaseResponse
     */
    public MmsBaseResponse createVideoLib(CreateLibRequest request) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, VERSION, VIDEO_LIB);
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * delete a video lib.
     *
     * @param libId video lib id.
     * @return BaseResponse
     */
    public MmsBaseResponse deleteVideoLib(String libId) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, new SourceRequest(), VERSION, VIDEO_LIB, libId);
        internalRequest.addParameter(DELETE_LIB_BY_ID, "");
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * create a image lib.
     *
     * @param request The params of create video lib.
     * @return BaseResponse
     */
    public MmsBaseResponse createImageLib(CreateLibRequest request) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, VERSION, IMAGE_LIB);
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * delete a image lib.
     *
     * @param libId image lib id.
     * @return BaseResponse
     */
    public MmsBaseResponse deleteImageLib(String libId) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, new SourceRequest(), VERSION, IMAGE_LIB, libId);
        internalRequest.addParameter(DELETE_LIB_BY_ID, "");
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * list lib of user.
     *
     * @param request The params of list user lib.
     * @return BaseResponse
     */
    public MmsListLibResponse listLib(ListLibRequest request) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, VERSION, LIB, LIST);
        return this.invokeHttpClient(internalRequest, MmsListLibResponse.class);
    }

    /**
     * list lib media.
     *
     * @param request The params of list lib media.
     * @return BaseResponse
     */
    public MmsListMediaResponse listMedia(ListMediaRequest request) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, VERSION, LIB, ITEM, LIST);
        return this.invokeHttpClient(internalRequest, MmsListMediaResponse.class);
    }

    /**
     * Insert a video into lib.
     *
     * @param libName The name of video lib.
     * @return BaseResponse
     */
    public MmsBaseResponse insertVideo(String libName, SourceAndDescRequest request) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.PUT, request, VERSION, VIDEO_LIB, libName);
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * Get insert video task result.
     *
     * @param libId The name of video lib id.
     * @return InsertVideoResultResponse
     */
    public InsertVideoResultResponse getInsertVideoResultById(String libId, String mediaId) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.GET, new SourceRequest(), VERSION, VIDEO_LIB, libId);
        internalRequest.addParameter(MEDIA_ID, mediaId);
        internalRequest.addParameter(GET_INSERT_RESPONSE_BY_ID, "");
        return this.invokeHttpClient(internalRequest, InsertVideoResultResponse.class);
    }

    /**
     * Get insert video task result by id.
     *
     * @param libName The name of video lib.
     * @return InsertVideoResultResponse
     */
    public InsertVideoResultResponse getInsertVideoResult(String libName, SourceRequest request) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, VERSION, VIDEO_LIB, libName);
        internalRequest.addParameter(SOURCE, request.getSource());
        return this.invokeHttpClient(internalRequest, InsertVideoResultResponse.class);
    }

    /**
     * Create search video by video task.
     *
     * @param libName The name of video lib.
     * @return MmsBaseResponse
     */
    public MmsBaseResponse searchVideoByVideo(String libName, SourceAndDescRequest request) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.POST, request, VERSION, VIDEO_LIB, libName);
        internalRequest.addParameter(SEARCH_BY_VIDEO, "");
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * Get search video by video task result by id.
     *
     * @param libName The name of video lib.
     * @return SearchVideoResponse
     */
    public SearchVideoResponse getSearchVideoByVideoResultById(String libName, String taskId) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, new SourceRequest(), VERSION, VIDEO_LIB, libName);
        internalRequest.addParameter(TASK_ID, taskId);
        internalRequest.addParameter(GET_SEARCH_RESPONSE_BY_TASK_ID, "");
        return this.invokeHttpClient(internalRequest, SearchVideoResponse.class);
    }

    /**
     * Get search video by video task result.
     *
     * @param libName The name of video lib.
     * @return SearchVideoResponse
     */
    public SearchVideoResponse getSearchVideoByVideoResult(String libName, SourceRequest request) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.GET, request, VERSION, VIDEO_LIB, libName);
        internalRequest.addParameter(SOURCE, request.getSource());
        internalRequest.addParameter(SEARCH_BY_VIDEO, "");
        return this.invokeHttpClient(internalRequest, SearchVideoResponse.class);
    }

    /**
     * Search video by image.
     *
     * @param libName The name of video lib.
     * @return SearchVideoResponse
     */
    public SearchVideoResponse searchVideoByImage(String libName, SourceAndDescRequest request) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.POST, request, VERSION, VIDEO_LIB, libName);
        internalRequest.addParameter(SOURCE, request.getSource());
        internalRequest.addParameter(SEARCH_BY_IMAGE, "");
        return this.invokeHttpClient(internalRequest, SearchVideoResponse.class);
    }

    /**
     * Delete a video from lib.
     *
     * @param libName The name of video lib.
     * @return MmsBaseResponse
     */
    public MmsBaseResponse deleteVideo(String libName, SourceRequest request) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.POST, request, VERSION, VIDEO_LIB, libName);
        internalRequest.addParameter(SOURCE, request.getSource());
        internalRequest.addParameter(DELETE_VIDEO, "");
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * Delete a video from lib.
     *
     * @param libId The id of video lib.
     * @return MmsBaseResponse
     */
    public MmsBaseResponse deleteVideoById(String libId, String mediaId) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, new SourceRequest(), VERSION, VIDEO_LIB, libId);
        internalRequest.addParameter(MEDIA_ID, mediaId);
        internalRequest.addParameter(DELETE_VIDEO_BY_ID, "");
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * Insert an image into lib.
     *
     * @param libName The name of image lib.
     * @return MmsBaseResponse
     */
    public MmsBaseResponse insertImage(String libName, SourceAndDescRequest request) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.PUT, request, VERSION, IMAGE_LIB, libName);
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * Search image by image.
     *
     * @param libName The name of image lib.
     * @return SearchImageResponse
     */
    public SearchImageResponse searchImageByImage(String libName, SourceAndDescRequest request) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.POST, request, VERSION, IMAGE_LIB, libName);
        internalRequest.addParameter(SEARCH_BY_IMAGE, "");
        return this.invokeHttpClient(internalRequest, SearchImageResponse.class);
    }

    /**
     * Delete an image from lib.
     *
     * @param libName The name of image lib.
     * @return MmsBaseResponse
     */
    public MmsBaseResponse deleteImage(String libName, SourceRequest request) {
        InternalRequest internalRequest =
            createRequest(HttpMethodName.POST, request, VERSION, IMAGE_LIB, libName);
        internalRequest.addParameter(SOURCE, request.getSource());
        internalRequest.addParameter(DELETE_IMAGE, "");
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    /**
     * Delete an image from lib.
     *
     * @param libId The id of image lib.
     * @return MmsBaseResponse
     */
    public MmsBaseResponse deleteImageById(String libId, String mediaId) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, new SourceRequest(), VERSION, IMAGE_LIB, libId);
        internalRequest.addParameter(MEDIA_ID, mediaId);
        internalRequest.addParameter(DELETE_IMAGE_BY_ID, "");
        return this.invokeHttpClient(internalRequest, MmsBaseResponse.class);
    }

    private InternalRequest createRequest(
        HttpMethodName httpMethod, AbstractBceRequest request, String... pathVariables) {
        // build URL paths
        List<String> pathComponents = new ArrayList<String>();

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri =
            HttpUtils.appendUri(
                getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(internalRequest, request);
        }
        return internalRequest;
    }

    private InternalRequest fillRequestPayload(
        InternalRequest internalRequest, AbstractBceRequest request) {
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
}
