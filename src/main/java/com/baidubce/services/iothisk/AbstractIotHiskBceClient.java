/*
 * Copyright 2018 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iothisk;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Abstract client for accessing the iot hisk service.
 */
public abstract class AbstractIotHiskBceClient extends AbstractBceClient {

    private static final String[] HEADERS_TO_SIGN = { Headers.HOST };
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    /**
     * Responsible for handling httpResponses from all hisk service calls.
     */
    protected static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceIotHiskJsonResponseHandler() };

    public AbstractIotHiskBceClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param signOptions The options for signature.
     * @param path The options for URI prefix.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific parameters to send.
     */
    protected InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          SignOptions signOptions, List<String> path, String... pathVariables) {
        if (path == null) {
            path = new ArrayList<String>();
        }

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        if (signOptions == null) {
            signOptions = new SignOptions();
            signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());

        if (httpMethod == HttpMethodName.PUT || httpMethod == HttpMethodName.POST) {
            fillInHeaderAndBody(bceRequest, request);
        }
        return request;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     *
     * @param bceRequest The original request, as created by the user.
     * @param request A request object, populated with endpoint, resource path, ready for callers to populate
     *                 any additional headers or parameters, and execute.
     */
    protected static void fillInHeaderAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    /**
     * Return a json byte array representation of the object.
     *
     * @param request request object to be parsed as json byte array.
     * @return a byte array representation of the object
     */
    protected static byte[] toJson(AbstractBceRequest request) {
        String jsonStr = JsonUtils.toJsonString(request);

        try {
            return jsonStr.getBytes(AbstractBceClient.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }

}
