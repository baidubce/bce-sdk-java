/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bci.model.common;

import java.util.List;

/**
 * HTTPGetAction describes an action based on HTTP Get requests.
 */
public class HTTPGetAction {
    /**
     * The path to access on the HTTP server
     */
    private String path;

    /**
     * The port of the container to send the HTTP request
     */
    private int port;

    /**
     * The host name to connect to, defaults to the pod IP
     */
    private String host;

    /**
     * The scheme to use for connecting to the host
     */
    private String scheme;

    /**
     * Custom headers to set in the request
     */
    private List<HTTPHeader> httpHeaders;

    /**
     * The constructor of HTTPGetAction.
     */
    public HTTPGetAction() {

    }

    /**
     * The constructor of HTTPGetAction.
     *
     * @param path The path to access on the HTTP server
     * @param port The port of the container to send the HTTP request
     * @param host The host name to connect to, defaults to the pod IP
     * @param scheme The scheme to use for connecting to the host
     * @param httpHeaders Custom headers to set in the request
     */
    public HTTPGetAction(String path, int port, String host, String scheme, List<HTTPHeader> httpHeaders) {
        this.path = path;
        this.port = port;
        this.host = host;
        this.scheme = scheme;
        this.httpHeaders = httpHeaders;
    }

    public String getPath() {
        return path;
    }

    public HTTPGetAction setPath(String path) {
        this.path = path;
        return this;
    }

    public int getPort() {
        return port;
    }

    public HTTPGetAction setPort(int port) {
        this.port = port;
        return this;
    }

    public String getHost() {
        return host;
    }

    public HTTPGetAction setHost(String host) {
        this.host = host;
        return this;
    }

    public String getScheme() {
        return scheme;
    }

    public HTTPGetAction setScheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    public List<HTTPHeader> getHttpHeaders() {
        return httpHeaders;
    }

    public HTTPGetAction setHttpHeaders(List<HTTPHeader> httpHeaders) {
        this.httpHeaders = httpHeaders;
        return this;
    }
}