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
package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for modifying the desc of the instance.
 */
public class ModifyInstanceDescRequest extends AbstractBceRequest {
    /**
     * The id of instance.
     */
    private String instanceId;

    /**
     * The new value for instance's desc.
     */
    private String desc;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Configure instanceId for the request.
     *
     * @param instanceId The id of the instance.
     * @return ModifyInstanceDescRequest with specified instanceId.
     */
    public ModifyInstanceDescRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    /**
     * Configure desc for the request.
     *
     * @param desc  The new value for instance's desc.
     * @return ModifyInstanceAttributesRequest with desc.
     */
    public ModifyInstanceDescRequest withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ModifyInstanceAttributesRequest with credentials.
     */
    @Override
    public ModifyInstanceDescRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
