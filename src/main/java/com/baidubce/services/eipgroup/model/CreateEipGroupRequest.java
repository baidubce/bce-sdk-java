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
package com.baidubce.services.eipgroup.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.eip.model.Billing;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for creating a nat.
 */
public class CreateEipGroupRequest extends AbstractBceRequest {

    /**
     * An ASCII string whose length is less than 64.
     *
     * The request will be idempotent if clientToken is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     *     BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The name of eip group.
     */
    private String name;

    /**
     * The eipCount of eip group.
     */
    private int eipCount;

    /**
     * The bandwidthInMbps of eip group.
     */
    private int bandwidthInMbps;

    /**
     * The billing of eip group
     */
    private Billing billing;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Configure name for the request.
     *
     * @param name The name of CreateNatRequest
     * @return CreateNatRequest with specific name
     */
    public CreateEipGroupRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Configure eipCount for the request.
     *
     * @param eipCount The eipCount of CreateEipGroupRequest
     * @return CreateEipGroupRequest with specific eipCount
     */
    public CreateEipGroupRequest withEipCount(int eipCount) {
        this.eipCount = eipCount;
        return this;
    }

    /**
     * Configure bandwidthInMbps for the request.
     *
     * @param bandwidthInMbps The bandwidthInMbps of CreateEipGroupRequest
     * @return CreateEipGroupRequest with specific bandwidthInMbps
     */
    public CreateEipGroupRequest withBandwidthInMbpst(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
        return this;
    }

    /**
     * Configure billing for the request.
     *
     * @param billing The spec of CreateNatRequest
     * @return CreateNatRequest with specific billing
     */
    public CreateEipGroupRequest withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEipCount() {
        return eipCount;
    }

    public void setEipCount(int eipCount) {
        this.eipCount = eipCount;
    }

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }
}
