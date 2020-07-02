/*
 * Copyright 2018-2019 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.vms;

import java.io.UnsupportedEncodingException;

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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.kms.model.Constants;
import com.baidubce.services.vms.model.LaunchCallRequest;
import com.baidubce.services.vms.model.LaunchCallResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * The client helping user access Voice Message Service
 */
public class VmsClient extends AbstractBceClient {
    private static final HttpResponseHandler[] responseHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public VmsClient(BceClientConfiguration config) {
        super(config, responseHandlers, false);
    }

    /**
     * Lauch a call to deliver voice message, text or voice file can be used in
     * request
     * 
     * @param request contain's all parameters used to launch a voice notification
     * @return response contain's the call's record id
     */
    public LaunchCallResponse launchCall(LaunchCallRequest request) {
        return invokeJsonCall(HttpMethodName.POST, "call", request, LaunchCallResponse.class);
    }

    /**
     * a helper method use to invoke json rest api method
     * 
     * @param <T>
     * @param method        http method
     * @param path          the resource path
     * @param request       request object that can be serialized to json
     * @param responseClass response object's class object
     * @return response object deserialized by json
     */
    protected <T extends AbstractBceResponse> T invokeJsonCall(HttpMethodName method, String path, Object request,
            Class<T> responseClass) {
        byte[] body;
        try {
            body = JsonUtils.toJsonString(request).getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException(Constants.FAIL_TO_GET_UTF8_BYTES, e);
        }
        InternalRequest internalRequest = new InternalRequest(method,
                HttpUtils.appendUri(this.getEndpoint(), URL_PREFIX, path));
        internalRequest.setContent(RestartableInputStream.wrap(body));
        internalRequest.addHeader(Headers.CONTENT_TYPE, Constants.APPLICATION_JSON);
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(body.length));
        return this.invokeHttpClient(internalRequest, responseClass);
    }
}
