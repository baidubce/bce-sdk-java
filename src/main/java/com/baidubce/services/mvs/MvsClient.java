/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
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

package com.baidubce.services.mvs;

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
import com.baidubce.services.mvs.model.InsertImageRequest;
import com.baidubce.services.mvs.model.InsertImageResponse;
import com.baidubce.services.mvs.model.InsertVideoRequest;
import com.baidubce.services.mvs.model.InsertVideoResponse;
import com.baidubce.services.mvs.model.SearchImageByImageRequest;
import com.baidubce.services.mvs.model.SearchImageByImageResponse;
import com.baidubce.services.mvs.model.SearchVideoByVideoRequest;
import com.baidubce.services.mvs.model.SearchVideoByVideoResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the client for accessing the BCE Multi-model Video Search service.
 */
public class MvsClient extends AbstractBceClient {
    private static final String VERSION = "v2";

    private static final String VIDEO_LIB = "videolib";

    private static final String IMAGE_LIB = "imagelib";

    private static final String SEARCH_BY_IMAGE = "searchByImage";

    private static final String SEARCH_BY_VIDEO = "searchByVideo";

    private static HttpResponseHandler[] mvsHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public MvsClient(BceClientConfiguration config) {
        super(config, mvsHandlers);
    }

    public InsertVideoResponse insertVideo(String lib, InsertVideoRequest insertVideoRequest) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.PUT, insertVideoRequest, VERSION, VIDEO_LIB, lib);
        return this.invokeHttpClient(internalRequest, InsertVideoResponse.class);
    }

    public SearchVideoByVideoResponse searchVideoByVideo(String lib,
                                                         SearchVideoByVideoRequest searchVideoByVideoRequest) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, searchVideoByVideoRequest, VERSION, VIDEO_LIB, lib);
        internalRequest.addParameter(SEARCH_BY_VIDEO, "");
        return this.invokeHttpClient(internalRequest, SearchVideoByVideoResponse.class);
    }

    public InsertImageResponse insertImage(String lib, InsertImageRequest insertImageRequest) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.PUT, insertImageRequest, VERSION, IMAGE_LIB, lib);
        return this.invokeHttpClient(internalRequest, InsertImageResponse.class);
    }

    public SearchImageByImageResponse searchImageByImage(String lib,
                                                         SearchImageByImageRequest searchImageByImageRequest) {
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, searchImageByImageRequest, VERSION, IMAGE_LIB, lib);
        internalRequest.addParameter(SEARCH_BY_IMAGE, "");
        return this.invokeHttpClient(internalRequest, SearchImageByImageResponse.class);
    }

    private InternalRequest createRequest(HttpMethodName httpMethod,
                                          AbstractBceRequest request,
                                          String... pathVariables) {
        // build URL paths
        List<String> pathComponents = new ArrayList<String>();

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

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
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
}
