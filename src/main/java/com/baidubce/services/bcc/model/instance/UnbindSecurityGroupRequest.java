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
 * request for bind securitygroup to instance
 */
public class UnbindSecurityGroupRequest extends AbstractBceRequest {
    /**
     * The id of instance.
     */
    @JsonIgnore
    private String instanceId;

    /**
     * The id of securitygroup.
     */
    private String securityGroupId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    /**
     * Configure the securityGroupId for the request.
     *
     * @param securityGroupId The id of the securitygroup.
     * @return UnbindSecurityGroupRequest with specified securityGroupId.
     */
    public UnbindSecurityGroupRequest withSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
        return this;
    }

    /**
     * Configure the instanceId for the request.
     *
     * @param instanceId The id of the instance.
     * @return UnbindSecurityGroupRequest with specified instanceId.
     */
    public UnbindSecurityGroupRequest withInstanceId(String instanceId) {
        this.setInstanceId(instanceId);
        return this;
    }
    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return UnbindSecurityGroupRequest with credentials.
     */
    @Override
    public UnbindSecurityGroupRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
