/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bbc.model.instance;


import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcc.model.TagModel;

import java.util.List;

/**
 * The response of bbc instance
 */
public class GetBbcInstanceResponse extends AbstractBceResponse {
    /**
     * The id of instance
     */
    private String id;
    /**
     * The name of instance
     */
    private String name;
    /**
     * The working status of instance
     */
    private String status;
    /**
     * The description of the instance.
     */
    private String desc;
    /**
     * The payment method of purchasing the instance,
     * see more detail in <a href = "https://bce.baidu.com/doc/BCC/API.html#Billing">BCE API doc</a>
     */
    private String paymentTiming;
    /**
     * The time when the instance was created
     */
    private String createTime;
    /**
     * The time when the instance will be expired.
     * If it's Postpaid, it will not have expired time.
     */
    private String expireTime;
    /**
     * The internal ip address for accessing.
     */
    private String internalIp;
    /**
     * The public ip address for accessing.
     */
    private String publicIp;
    /**
     * The id of flavor which belongs to instance
     */
    private String flavorId;
    /**
     * The id which was used to build the instance.
     */
    private String imageId;
    /**
     * The total bandwidth in Mbps for the instance.
     */
    private int networkCapacityInMbps;
    /**
     * The region where the instance based on
     */
    private String region;
    /**
     * The name of avaliable zone
     */
    private String zone;
    /**
     * whether the instacne is auto renew or not
     */
    private Boolean autoRenew;

    /**
     * The list of bonded tags.
     */
    private List<TagModel> tags;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getFlavorId() {
        return flavorId;
    }

    public void setFlavorId(String flavorId) {
        this.flavorId = flavorId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public int getNetworkCapacityInMbps() {
        return networkCapacityInMbps;
    }

    public void setNetworkCapacityInMbps(int networkCapacityInMbps) {
        this.networkCapacityInMbps = networkCapacityInMbps;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }
}
