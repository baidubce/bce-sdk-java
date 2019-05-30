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

public class Eip {

    /**
     * The name of eip.
     */
    private String name;

    /**
     * The ip of eip.
     */
    private String eip;

    /**
     * The status of eip.
     */
    private String status;

    /**
     * The eipInstanceType of eip.
     */
    private String eipInstanceType;

    /**
     * The instanceType of eip.
     */
    private String instanceType;

    /**
     * The instanceId of eip.
     */
    private String instanceId;

    /**
     * The shareGroupId of eip.
     */
    private String shareGroupId;

    /**
     * The paymentTiming of eip.
     */
    private String paymentTiming;

    /**
     * The bandwidthInMbps of eip.
     */
    private int bandwidthInMbps;

    /**
     * The billingMethod of eip.
     */
    private String billingMethod;

    /**
     * The createTime of eip.
     */
    private String createTime;

    /**
     * The expireTime of eip.
     */
    private String expireTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEip() {
        return eip;
    }

    public void setEip(String eip) {
        this.eip = eip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEipInstanceType() {
        return eipInstanceType;
    }

    public void setEipInstanceType(String eipInstanceType) {
        this.eipInstanceType = eipInstanceType;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getShareGroupId() {
        return shareGroupId;
    }

    public void setShareGroupId(String shareGroupId) {
        this.shareGroupId = shareGroupId;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
