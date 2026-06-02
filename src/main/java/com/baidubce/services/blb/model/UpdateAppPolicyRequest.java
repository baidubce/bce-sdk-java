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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The request for batch updating appBlb policies.
 */
public class UpdateAppPolicyRequest extends AbstractBceRequest {

    /**
     * the blb id of the policy.
     */
    @JsonIgnore
    private String blbId;

    /**
     * the listener port of the policies to update.
     */
    private Integer port;

    /**
     * the listener type of the policy (e.g. TCP/UDP/HTTP/HTTPS/SSL),
     * required when same port has multiple protocols.
     */
    private String type;

    /**
     * the list of policy items to update.
     */
    private List<UpdateAppPolicyItem> policyList;

    /**
     * An ASCII string whose length is less than 64.
     * The request will be idempotent if clientToken is provided.
     */
    @JsonIgnore
    private String clientToken;

    public String getBlbId() {
        return blbId;
    }

    public void setBlbId(String blbId) {
        this.blbId = blbId;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<UpdateAppPolicyItem> getPolicyList() {
        return policyList;
    }

    public void setPolicyList(List<UpdateAppPolicyItem> policyList) {
        this.policyList = policyList;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public UpdateAppPolicyRequest withBlbId(String blbId) {
        this.blbId = blbId;
        return this;
    }

    public UpdateAppPolicyRequest withPort(Integer port) {
        this.port = port;
        return this;
    }

    public UpdateAppPolicyRequest withType(String type) {
        this.type = type;
        return this;
    }

    public UpdateAppPolicyRequest withPolicyList(List<UpdateAppPolicyItem> policyList) {
        this.policyList = policyList;
        return this;
    }

    @Override
    public UpdateAppPolicyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
