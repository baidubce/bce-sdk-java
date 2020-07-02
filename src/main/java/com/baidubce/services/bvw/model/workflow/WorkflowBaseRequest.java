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
package com.baidubce.services.bvw.model.workflow;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Base workflow request, contains a attribute of workflow name.
 */
public class WorkflowBaseRequest extends AbstractBceRequest {

    /**
     * The workflow name.
     */
    String name;

    @Override
    public WorkflowBaseRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Construct a base workflow request with workflow name.
     * @param   name The workflow name
     * @return  A get workflow request
     */
    public static WorkflowBaseRequest of(String name) {
        WorkflowBaseRequest baseRequest = new WorkflowBaseRequest();
        baseRequest.setName(name);
        return baseRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
