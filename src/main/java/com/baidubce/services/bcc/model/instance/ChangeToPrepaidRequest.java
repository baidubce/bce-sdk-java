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
package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for changing the instance payment to prepaid.
 */
public class ChangeToPrepaidRequest extends AbstractBceRequest {

    /**
     * The id of instance.
     */
    private String instanceId;

    /**
     * Purchase duration, the unit is month.
     */
    private int duration;

    /**
     * Whether is change the related CDS or not.
     */
    private boolean relationCds = true;

    private Boolean autoPay;

    private Boolean autoRenew = false;

    private Integer autoRenewPeriod = 1;

    public ChangeToPrepaidRequest withAutoPay(boolean autoPay) {
        this.autoPay = autoPay;
        return this;
    }

    public Boolean getAutoPay() {
        return autoPay;
    }

    public void setAutoPay(Boolean autoPay) {
        this.autoPay = autoPay;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public ChangeToPrepaidRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ChangeToPrepaidRequest withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public boolean isRelationCds() {
        return relationCds;
    }

    public void setRelationCds(boolean relationCds) {
        this.relationCds = relationCds;
    }

    public ChangeToPrepaidRequest withRelationCds(boolean relationCds) {
        this.relationCds = relationCds;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ChangeToPrepaidRequest with credentials.
     */
    @Override
    public ChangeToPrepaidRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public ChangeToPrepaidRequest withAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
        return this;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public ChangeToPrepaidRequest withAutoRenewPeriod(Integer autoRenewPeriod) {
        this.autoRenewPeriod = autoRenewPeriod;
        return this;
    }

    public Integer getAutoRenewPeriod() {
        return autoRenewPeriod;
    }

    public void setAutoRenewPeriod(Integer autoRenewPeriod) {
        this.autoRenewPeriod = autoRenewPeriod;
    }
}
