/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdm.model;

import com.baidubce.model.AbstractBceResponse;

public class AccessDetailResponse extends AbstractBceResponse {
    /**
     * Tcp endpoint address .
     */
    private String tcpEndpoint;
    /**
     * Ssl endpoint address .
     */
    private String sslEndpoint;
    /**
     * Wss endpoint address .
     */
    private String wssEndpoint;
    /**
     * Username used for Iothub .
     */
    private String username;
    /**
     * Access key used for Iothub .
     */
    private String key;

    public String getTcpEndpoint() {
        return tcpEndpoint;
    }

    public void setTcpEndpoint(String tcpEndpoint) {
        this.tcpEndpoint = tcpEndpoint;
    }

    public String getSslEndpoint() {
        return sslEndpoint;
    }

    public void setSslEndpoint(String sslEndpoint) {
        this.sslEndpoint = sslEndpoint;
    }

    public String getWssEndpoint() {
        return wssEndpoint;
    }

    public void setWssEndpoint(String wssEndpoint) {
        this.wssEndpoint = wssEndpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
