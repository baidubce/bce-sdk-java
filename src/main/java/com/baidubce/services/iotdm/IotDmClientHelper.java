/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iotdm;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
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

class IotDmClientHelper {

    private static final String VERSION = "v1";
    private static final String IOT = "iot";
    private static final String MANAGEMENT = "management";

    private static final String[] HEADERS_TO_SIGN = { Headers.HOST, Headers.BCE_DATE };
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    /**
     * Responsible for handling HttpResponse from all iot device management service calls.
     */
    static final HttpResponseHandler[] IOT_DM_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler()
    };

    static InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
            URI endpoint, SignOptions signOptions, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.addAll(Arrays.asList(VERSION, IOT, MANAGEMENT));
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        if (signOptions == null) {
            signOptions = SignOptions.DEFAULT;
            signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        }

        URI uri = HttpUtils.appendUri(endpoint, path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());

        if (httpMethod == HttpMethodName.PUT || httpMethod == HttpMethodName.POST) {
            fillInHeaderAndBody(bceRequest, request);
        }

        return request;
    }

    private static void fillInHeaderAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    private static byte[] toJson(AbstractBceRequest request) {
        String jsonStr = JsonUtils.toJsonString(request);
        try {
            return jsonStr.getBytes(AbstractBceClient.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }

}
