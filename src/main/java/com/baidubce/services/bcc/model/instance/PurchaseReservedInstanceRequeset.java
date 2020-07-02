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
import com.baidubce.services.bcc.model.Billing;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for renewing the instance.
 */
public class PurchaseReservedInstanceRequeset extends AbstractBceRequest {
    /**
     * An ASCII string whose length is less than 64.
     * Configure optional client token for the request. The request will be idempotent if client token is provided.
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
     * The detail model to specify the billing.
     */
    private Billing billing;

    /**
     * The flag of instance related renew.
     * see all of supported flag type in {@link com.baidubce.services.bcc.model.instance.RelatedRenewFlagType}
     */
    private String relatedRenewFlag;

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
     * @return PurchaseReservedInstanceRequeset with specific clientToken
     */
    public PurchaseReservedInstanceRequeset withClientToken(String clientToken) {
        this.clientToken = clientToken;
        return this;
    }

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
     * @return PurchaseReservedInstanceRequeset with instanceId.
     */
    public PurchaseReservedInstanceRequeset withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    /**
     * Configure billing for the request.
     *
     * @param billing The detail model to specify the billing.
     * @return PurchaseReservedInstanceRequeset with specific billing
     */
    public PurchaseReservedInstanceRequeset withBilling(Billing billing) {
        this.billing = billing;
        return this;
    }

    public String getRelatedRenewFlag() {
        return relatedRenewFlag;
    }

    public void setRelatedRenewFlag(String relatedRenewFlag) {
        this.relatedRenewFlag = relatedRenewFlag;
    }

    /**
     * Configure relatedRenewFlag for the request.
     * see all of supported flag type in {@link com.baidubce.services.bcc.model.instance.RelatedRenewFlagType}
     *
     * @param relatedRenewFlag The flag of instance related renew.
     * @return PurchaseReservedInstanceRequeset with specific relatedRenewFlag
     */
    public PurchaseReservedInstanceRequeset withRelatedRenewFlag(String relatedRenewFlag) {
        this.relatedRenewFlag = relatedRenewFlag;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return PurchaseReservedInstanceRequeset with credentials.
     */
    @Override
    public PurchaseReservedInstanceRequeset withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
