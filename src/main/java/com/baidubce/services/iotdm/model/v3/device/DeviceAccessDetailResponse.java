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
package com.baidubce.services.iotdm.model.v3.device;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response of getting the access detail of a device.
 */
public class DeviceAccessDetailResponse extends AbstractBceResponse {

    private String tcpEndpoint;

    private String sslEndpoint;

    private String username;

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
