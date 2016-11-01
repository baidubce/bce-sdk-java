/*
 * Copyright 2016 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iothub.model;

/**
 * Represent the response to query the specified endpoint.
 */
public class QueryEndpointResponse extends BaseResponse {
    private String uuid;

    private String createTime;

    private String hostname;

    private String websocketHostname;

    private String mqttHostname;

    private String mqttTlsHostname;

    private String endpointName;

    private String accountUuid;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getWebsocketHostname() {
        return websocketHostname;
    }

    public void setWebsocketHostname(String websocketHostname) {
        this.websocketHostname = websocketHostname;
    }

    public String getMqttHostname() {
        return mqttHostname;
    }

    public void setMqttHostname(String mqttHostname) {
        this.mqttHostname = mqttHostname;
    }

    public String getMqttTlsHostname() {
        return mqttTlsHostname;
    }

    public void setMqttTlsHostname(String mqttTlsHostname) {
        this.mqttTlsHostname = mqttTlsHostname;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public String getUuid() {

        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
