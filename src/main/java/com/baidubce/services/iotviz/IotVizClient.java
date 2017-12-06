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
package com.baidubce.services.iotviz;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
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
import com.baidubce.services.iotviz.model.CreateTokenRequest;
import com.baidubce.services.iotviz.model.CreateTokenResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Provides the client for accessing iot viz services.
 */
public class IotVizClient extends AbstractBceClient {

    private static final String ENDPOINT_HOST = "viz.baidubce.com";
    private static final String VERSION = "v2";
    private static final String[] HEADERS_TO_SIGN = {Headers.HOST, Headers.BCE_DATE};

    private static final String TOKEN = "tokens";

    /**
     * Responsible for handling HttpResponse from all IotViz service calls.
     */
    private static final HttpResponseHandler[] IOTVIZ_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public IotVizClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT_HOST) : config, IOTVIZ_HANDLERS);
    }

    /**
     * Create a token
     * @param ttl - token's time to live, in seconds
     * @return a string token
     */
    public String createToken(int ttl) {
        checkArgument(ttl > 0);

        CreateTokenRequest request = new CreateTokenRequest();
        request.setTtl(ttl);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, null, TOKEN);
        CreateTokenResponse response = this.invokeHttpClient(internalRequest, CreateTokenResponse.class);
        return response.getToken();
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          SignOptions signOptions, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);

        if (signOptions == null) {
            signOptions = SignOptions.DEFAULT;
            signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        }
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillInHeaderAndBody(bceRequest, request);
        }

        return request;
    }

    private void fillInHeaderAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] bytes = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(bytes.length));
        request.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(bytes));
    }

    private byte[] toJson(AbstractBceRequest bceRequest) {
        String jsonStr = JsonUtils.toJsonString(bceRequest);
        try {
            return jsonStr.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }
}
