/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap.model.deploygroup;

/**
 * The model for http get action.
 */
public class HTTPGetActionModel {

    /**
     * The path of url.
     */
    private String path;

    /**
     * The port.
     */
    private int port;

    /**
     * The host info.
     */
    private String host;

    /**
     * The scheme.
     */
    private String scheme;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public HTTPGetActionModel withPath(String path) {
        this.setPath(path);
        return this;
    }

    public HTTPGetActionModel withPort(int port) {
        this.setPort(port);
        return this;
    }

    public HTTPGetActionModel withHost(String host) {
        this.setHost(host);
        return this;
    }

    public HTTPGetActionModel withScheme(String scheme) {
        this.setScheme(scheme);
        return this;
    }

}
