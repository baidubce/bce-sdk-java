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
package com.baidubce.services.bcc.model;

import java.util.Date;

/**
 * instance detail info model
 */
public class InstanceModel {

    /**
     * The identified id of the instance
     */
    private String id;

    /**
     * The name of the instance.
     */
    private String name;

    /**
     * The status of the instance,
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#InstanceStatus">BCE API doc</>
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
    private Date createTime;

    /**
     * The time when the instance will be expired.
     * If it's Postpaid, it will not have expired time.
     */
    private Date expireTime;

    /**
     * The internal ip address for accessing.
     */
    private String internalIp;

    /**
     * The public ip address for accessing.
     */
    private String publicIp;

    /**
     * The available count of CPU within the instance
     */
    private int cpuCount;

    /**
     * The total size of memory in GB for the instance.
     */
    private int memoryCapacityInGB;

    /**
     * The total size of temporary volume in GB,exclude the system volume.
     */
    private int localDiskSizeInGB;

    /**
     * The id which was used to build the instance.
     */
    private String imageId;

    /**
     * The total bandwidth in Mbps for the instance.
     */
    private int networkCapacityInMbps;

    /**
     * the name of available zone
     */
    private String zoneName;

    /**
     * The policy of the instance placement
     * when "default",the instance has been placed by system auto, while "dedicatedHost", stands for been placed at dcc
     */
    private String placementPolicy;

    private String subnetId;

    private String vpcId;

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

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public int getMemoryCapacityInGB() {
        return memoryCapacityInGB;
    }

    public void setMemoryCapacityInGB(int memoryCapacityInGB) {
        this.memoryCapacityInGB = memoryCapacityInGB;
    }

    public int getLocalDiskSizeInGB() {
        return localDiskSizeInGB;
    }

    public void setLocalDiskSizeInGB(int localDiskSizeInGB) {
        this.localDiskSizeInGB = localDiskSizeInGB;
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

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getPlacementPolicy() {
        return placementPolicy;
    }

    public void setPlacementPolicy(String placementPolicy) {
        this.placementPolicy = placementPolicy;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    @Override
    public String toString() {
        return "InstanceModel{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", status='" + status + '\''
                + ", desc='" + desc + '\''
                + ", paymentTiming='" + paymentTiming + '\''
                + ", createTime='" + createTime + '\''
                + ", expireTime='" + expireTime + '\''
                + ", internalIp='" + internalIp + '\''
                + ", publicIp='" + publicIp + '\''
                + ", cpuCount=" + cpuCount
                + ", memoryCapacityInGB=" + memoryCapacityInGB
                + ", localDiskSizeInGB=" + localDiskSizeInGB
                + ", imageId='" + imageId + '\''
                + ", networkCapacityInMbps=" + networkCapacityInMbps
                + ", placementPolicy='" + placementPolicy + '\''
                + ", zoneName='" + zoneName + '\''
                + ", subnetId='" + subnetId + '\''
                + ", vpcId='" + vpcId + '\''
                + '}';
    }
}
