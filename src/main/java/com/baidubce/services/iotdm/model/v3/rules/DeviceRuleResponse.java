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
package com.baidubce.services.iotdm.model.v3.rules;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class DeviceRuleResponse extends AbstractBceResponse {
    /**
     * ID of Device Rule .
     */
    private String id;
    /**
     * Device name of Device Rule .
     */
    private String deviceName;
    /**
     * Name of Device Rule .
     */
    private String name;
    /**
     * List of Device Rule Source detail .
     */
    private List<DeviceRuleSourceDetail> sources;
    /**
     * List of Device Rule destination detail .
     */
    private List<DeviceRuleDestinationDetail> destinations;
    /**
     * Whether open Device Rule or not .
     */
    private Boolean enable;
    /**
     * Create time of Device Rule .
     */
    private Long createTime;
    /**
     * Update time of Device Rule .
     */
    private Long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeviceRuleSourceDetail> getSources() {
        return sources;
    }

    public void setSources(List<DeviceRuleSourceDetail> sources) {
        this.sources = sources;
    }

    public List<DeviceRuleDestinationDetail> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DeviceRuleDestinationDetail> destinations) {
        this.destinations = destinations;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
