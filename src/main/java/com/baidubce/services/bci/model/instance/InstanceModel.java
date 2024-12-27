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

package com.baidubce.services.bci.model.instance;

import com.baidubce.BceConstants;
import com.baidubce.services.bci.model.common.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

/**
 * The response for get instance
 */
public class InstanceModel {

    /**
     * The id of the instance
     */
    private String instanceId;

    /**
     * The name of the instance
     */
    private String instanceName;

    /**
     * The status of the instance
     */
    private String status;

    /**
     * The zoneName of the instance
     */
    private String zoneName;

    /**
     * The cpuType of the instance
     */
    private String cpuType;

    /**
     * The gpuType of the instance
     */
    private String gpuType;

    /**
     * The cpu count of the instance
     */
    private float cpu;

    /**
     * The memory count of the instance
     */
    private float memory;

    /**
     * The bandwidthInMbps of the instance eip
     */
    private Integer bandwidthInMbps;

    /**
     * The publicIp of the instance
     */
    private String publicIp;

    /**
     * The internalIp of the instance
     */
    private String internalIp;

    /**
     * The createTime of the instance
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Timestamp createTime;

    /**
     * The updateTime of the instance
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Timestamp updateTime;

    /**
     * The deleteTime of the instance
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Timestamp deleteTime;

    /**
     * The restartPolicy of the instance
     */
    private String restartPolicy;

    /**
     * The tags of the instance
     */
    private List<Tag> tags;

    public String getInstanceId() {
        return instanceId;
    }

    public InstanceModel setInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public InstanceModel setInstanceName(String instanceName) {
        this.instanceName = instanceName;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public InstanceModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getZoneName() {
        return zoneName;
    }

    public InstanceModel setZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public String getCpuType() {
        return cpuType;
    }

    public InstanceModel setCpuType(String cpuType) {
        this.cpuType = cpuType;
        return this;
    }

    public String getGpuType() {
        return gpuType;
    }

    public InstanceModel setGpuType(String gpuType) {
        this.gpuType = gpuType;
        return this;
    }

    public float getCpu() {
        return cpu;
    }

    public InstanceModel setCpu(float cpu) {
        this.cpu = cpu;
        return this;
    }

    public float getMemory() {
        return memory;
    }

    public InstanceModel setMemory(float memory) {
        this.memory = memory;
        return this;
    }

    public Integer getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public InstanceModel setBandwidthInMbps(Integer bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
        return this;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public InstanceModel setPublicIp(String publicIp) {
        this.publicIp = publicIp;
        return this;
    }

    public String getInternalIp() {
        return internalIp;
    }

    public InstanceModel setInternalIp(String internalIp) {
        this.internalIp = internalIp;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public InstanceModel setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public InstanceModel setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public InstanceModel setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
        return this;
    }

    public String getRestartPolicy() {
        return restartPolicy;
    }

    public InstanceModel setRestartPolicy(String restartPolicy) {
        this.restartPolicy = restartPolicy;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public InstanceModel setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }
}
