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

import lombok.ToString;

import java.util.List;

/**
 * Eip group model.
 */
@ToString
public class EipGroup {

    /**
     * The id of EipGroup.
     */
    private String id;

    /**
     * The name of EipGroup.
     */
    private String name;

    /**
     * The eips of EipGroup.
     */
    private List<Eip> eips;

    /**
     * The status of EipGroup.
     */
    private String status;

    /**
     * The bandwidthInMbps of EipGroup.
     */
    private int bandwidthInMbps;

    /**
     * The paymentTiming of EipGroup.
     */
    private String paymentTiming;

    /**
     * The createTime of EipGroup.
     */
    private String createTime;

    /**
     * The expireTime of EipGroup.
     */
    private String expireTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Eip> getEips() {
        return eips;
    }

    public void setEips(List<Eip> eips) {
        this.eips = eips;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBandwidthInMbps() {
        return bandwidthInMbps;
    }

    public void setBandwidthInMbps(int bandwidthInMbps) {
        this.bandwidthInMbps = bandwidthInMbps;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
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
