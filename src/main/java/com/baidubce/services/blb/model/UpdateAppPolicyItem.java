/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.blb.model;

/**
 * The policy item for updating appBlb policy.
 */
public class UpdateAppPolicyItem {

    /**
     * the id of the policy to update.
     */
    private String policyId;

    /**
     * the priority of the policy.
     */
    private Integer priority;

    /**
     * the description of the policy.
     */
    private String description;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UpdateAppPolicyItem withPolicyId(String policyId) {
        this.policyId = policyId;
        return this;
    }

    public UpdateAppPolicyItem withPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public UpdateAppPolicyItem withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "UpdateAppPolicyItem{" +
                "policyId='" + policyId + '\'' +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                '}';
    }
}
