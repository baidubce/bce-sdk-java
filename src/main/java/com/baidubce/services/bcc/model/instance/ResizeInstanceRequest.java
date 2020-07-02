/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcc.model.volume.EphemeralDisk;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The request for resizing instance.
 */
public class ResizeInstanceRequest extends AbstractBceRequest {
    /**
     * An ASCII string whose length is less than 64.
     * The request will be idempotent if client token is provided.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *     BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The id of instance.
     */
    @JsonIgnore
    private String instanceId;

    /**
     * The spec of instance
     */
    private String spec;

    /**
     * The parameter of specified the cpu core to resize the instance.
     */
    private int cpuCount;

    /**
     * The parameter of specified the capacity of memory in GB to resize the instance.
     */
    private int memoryCapacityInGB;


    /**
     * The parameter of ephemeral disk capacity. Currently, this parameter only supports the DCC instances
     * of storage type. And the capacity will be deducted from the corresponding capacity of the DCC flavor.
     */
    private List<EphemeralDisk> ephemeralDisks;


    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * Configure the instanceId for the request.
     *
     * @param instanceId The id of instance.
     * @return ResizeInstanceRequest with specific instanceId.
     */
    public ResizeInstanceRequest withInstanceId(String instanceId) {
        this.setInstanceId(instanceId);
        return this;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public ResizeInstanceRequest withSpec(String spec) {
        this.setSpec(spec);
        return this;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    /**
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
     *
     * @param clientToken An ASCII string whose length is less than 64.
     *                    See more detail at
     *                    <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *                        BCE API doc</a>
     * @return ResizeInstanceRequest with specific clientToken
     */
    public ResizeInstanceRequest withClientToken(String clientToken) {
        this.clientToken = clientToken;
        return this;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    /**
     * Configure request cpuCount for the request.
     *
     * @param cpuCount The parameter of specified the cpu core to resize the instance.
     * @return ResizeInstanceRequest with cpuCount.
     */
    public ResizeInstanceRequest withCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
        return this;
    }

    public int getMemoryCapacityInGB() {
        return memoryCapacityInGB;
    }

    public void setMemoryCapacityInGB(int memoryCapacityInGB) {
        this.memoryCapacityInGB = memoryCapacityInGB;
    }

    /**
     * Configure request memoryCapacityInGB for the request.
     *
     * @param memoryCapacityInGB The parameter of specified the capacity of memory in GB to resize the instance.
     * @return ResizeInstanceRequest with memoryCapacityInGB.
     */
    public ResizeInstanceRequest withMemoryCapacityInGB(int memoryCapacityInGB) {
        this.memoryCapacityInGB = memoryCapacityInGB;
        return this;
    }

    public List<EphemeralDisk> getEphemeralDisks() {
        return ephemeralDisks;
    }

    public void setEphemeralDisks(List<EphemeralDisk> ephemeralDisks) {
        this.ephemeralDisks = ephemeralDisks;
    }

    /**
     * Configure request ephemeralDisks for the request.
     *
     * @param ephemeralDisks The parameter of ephemeral disk capacity. Currently, this parameter only supports
     *                       the DCC instances of storage type. And the capacity will be deducted from
     *                       the corresponding capacity of the DCC flavor.
     * @return ResizeInstanceRequest with ephemeralDisks.
     */
    public ResizeInstanceRequest withEphemeralDisks(List<EphemeralDisk> ephemeralDisks) {
        this.ephemeralDisks = ephemeralDisks;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ResizeInstanceRequest with credentials.
     */
    @Override
    public ResizeInstanceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
