/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw.model.workflow.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Base instance request.
 */
public class InstanceBaseRequest extends AbstractBceRequest {

    /**
     * The instance id.
     */
    private String instanceId;

    /**
     * Construct a base instance request with specified parameters.
     *
     * @param instanceId The instance id
     * @return A base instance response
     */
    public static InstanceBaseRequest of(String instanceId) {
        InstanceBaseRequest baseRequest = new InstanceBaseRequest();
        baseRequest.setInstanceId(instanceId);
        return baseRequest;
    }

    @Override
    public InstanceBaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

}
