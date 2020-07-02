/*
 * Copyright (c) 2020 Baidu. All Rights Reserved.
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
package com.baidubce.internal;

import java.net.URI;
import java.util.Map;

import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.google.common.collect.Maps;

/**
 * Represents a base request being sent to a BCE Service, including the
 * parameters being sent as part of the request, the endpoint to which the
 * request should be sent, etc.
 *
 * @author chenjiayi05
 * @date 2020/04/28
 */
public class BaseRequest {

    /**
     * The default protocol to construct URI
     */
    private static final String DEFAULT_PROTOCOL_PREFIX = "http://";

    /**
     * The HTTP method to use when sending this request.
     */
    private HttpMethodName httpMethod;

    /**
     * The path to which this request should be sent
     */
    private String path;

    /**
     * Map of the parameters being sent as part of this request.
     */
    private Map<String, String> parameters = Maps.newHashMap();

    /**
     * Map of the headers included in this request
     */
    private Map<String, String> headers = Maps.newHashMap();

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void addParameter(String name, String value) {
        this.parameters.put(name, value);
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public HttpMethodName getHttpMethod() {
        return this.httpMethod;
    }

    public void setHttpMethod(HttpMethodName httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    public BaseRequest(HttpMethodName httpMethod, String path, Map<String, String> parameters,
                       Map<String, String> headers) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.parameters = parameters;
        this.headers = headers;
    }

    /**
     * transfer from base request to internal request
     *
     * @param request base request
     * @return internal request
     */
    public static InternalRequest toInternalRequest(BaseRequest request) {
        Map<String, String> headers = request.getHeaders();
        String host;

        // deal with the default header "Host"
        if (headers.containsKey(Headers.HOST)) {
            host = headers.get(Headers.HOST);
        } else {
            host = headers.get(Headers.HOST.toLowerCase());
            headers.remove(Headers.HOST.toLowerCase());
        }

        URI uri = URI.create(DEFAULT_PROTOCOL_PREFIX + host + request.getPath());
        InternalRequest res = new InternalRequest(request.getHttpMethod(), uri);
        res.setParameters(request.getParameters());
        res.setHeaders(headers);
        return res;
    }
}
