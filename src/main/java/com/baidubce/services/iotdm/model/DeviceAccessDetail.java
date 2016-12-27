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
package com.baidubce.services.iotdm.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response of get device access detail.
 */
public class DeviceAccessDetail extends AbstractBceResponse {

    private String protocol;
    private String deviceName;
    private String endpointName;
    private List<String> endpoints;
    private List<String> pubTopics;
    private List<String> subTopics;

    public String getProtocol() {
        return protocol;
    }
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getEndpointName() {
        return endpointName;
    }
    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }
    public List<String> getEndpoints() {
        return endpoints;
    }
    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }
    public List<String> getPubTopics() {
        return pubTopics;
    }
    public void setPubTopics(List<String> pubTopics) {
        this.pubTopics = pubTopics;
    }
    public List<String> getSubTopics() {
        return subTopics;
    }
    public void setSubTopics(List<String> subTopics) {
        this.subTopics = subTopics;
    }

}
