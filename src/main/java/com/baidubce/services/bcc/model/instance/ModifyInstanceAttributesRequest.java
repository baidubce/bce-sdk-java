/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for modifying the attribute of the instance.
 */
public class ModifyInstanceAttributesRequest extends AbstractBceRequest {

    /**
     * The id of instance.
     */
    @JsonIgnore
    private String instanceId;

    /**
     * The new value for instance's name.
     */
    private String name;

    private Integer netEthQueueCount;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * Configure instanceId for the request.
     *
     * @param instanceId The id of the instance.
     * @return ModifyInstanceAttributesRequest with specified instanceId.
     */
    public ModifyInstanceAttributesRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Configure name for the request.
     *
     * @param name  The new value for instance's name.
     * @return ModifyInstanceAttributesRequest with name.
     */
    public ModifyInstanceAttributesRequest withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNetEthQueueCount() {
        return netEthQueueCount;
    }

    public void setNetEthQueueCount(Integer netEthQueueCount) {
        this.netEthQueueCount = netEthQueueCount;
    }

    public ModifyInstanceAttributesRequest withNetEthQueueCount(Integer netEthQueueCount) {
        this.netEthQueueCount = netEthQueueCount;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ModifyInstanceAttributesRequest with credentials.
     */
    @Override
    public ModifyInstanceAttributesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
