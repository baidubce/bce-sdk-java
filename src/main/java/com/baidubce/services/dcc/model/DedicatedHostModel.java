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
package com.baidubce.services.dcc.model;

import java.util.Date;

/**
 * dcc detail info model
 */
public class DedicatedHostModel {
    /**
     * The identified id of dcc
     */
    private String id;

    /**
     * The name of dcc
     */
    private String name;

    /**
     * The status of dcc
     */
    private String status;

    /**
     * The description of dcc
     */
    private String desc;

    /**
     * The name of flavor
     */
    private String flavorName;

    /**
     * dcc resource usage detail
     */
    private ResourceUsage resourceUsage;

    /**
     * The payment method of purchasing the dcc,
     * see more detail in <a href = "https://cloud.baidu.com/doc/DCC/API.html#Billing">BCE API doc</a>
     */
    private String paymentTiming;

    /**
     * The time when the instance was created
     */
    private Date createTime;

    /**
     * The time when the instance will be expired.
     * If it's Postpaid, it will not have expired time.
     */
    private Date expireTime;

    /**
     * the name of available zone
     */
    private String zoneName;

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

    public String getFlavorName() {
        return flavorName;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
    }

    public ResourceUsage getResourceUsage() {
        return resourceUsage;
    }

    public void setResourceUsage(ResourceUsage resourceUsage) {
        this.resourceUsage = resourceUsage;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public String toString() {
        return "DedicatedHostModel{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", status='" + status + '\''
                + ", desc='" + desc + '\''
                + ", flavorName='" + flavorName + '\''
                + ", resourceUsage=" + resourceUsage
                + ", paymentTiming='" + paymentTiming + '\''
                + ", createTime=" + createTime
                + ", expireTime=" + expireTime
                + ", zoneName='" + zoneName + '\''
                + '}';
    }
}
