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
package com.baidubce.services.bcc.model;

/**
 * volume detail info model
 */
public class VolumeClusterModel {

    /**
     * The id of the cluster
     */
    private String clusterId;

    /**
     * The name of the cluster
     */
    private String clusterName;

    /**
     * The payment method of purchasing the volume,
     * see more detail in <a href = "https://bce.baidu.com/doc/BCC/API.html#Billing">BCE API doc</a>
     */
    private String productType;

    /**
     * The time when the cluster was created.
     */
    private String createdTime;

    /**
     * The time when the cluster will be expired.
     */
    private String expiredTime;

    /**
     * The status of the volume,
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#VolumeStatus">BCE API doc</a>
     */
    private String status;

    private String clusterType;

    /**
     * the name of available zone
     */
    private String logicalZone;

    private int totalCapacity;

    private int usedCapacity;

    private int availableCapacity;

    private int expandingCapacity;

    private int createdVolumeNum;

    private Boolean enableAutoRenew = false;

    private String renewTimeUnit;

    private Integer renewTime;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClusterType() {
        return clusterType;
    }

    public void setClusterType(String clusterType) {
        this.clusterType = clusterType;
    }

    public String getLogicalZone() {
        return logicalZone;
    }

    public void setLogicalZone(String logicalZone) {
        this.logicalZone = logicalZone;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(int usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public int getExpandingCapacity() {
        return expandingCapacity;
    }

    public void setExpandingCapacity(int expandingCapacity) {
        this.expandingCapacity = expandingCapacity;
    }

    public int getCreatedVolumeNum() {
        return createdVolumeNum;
    }

    public void setCreatedVolumeNum(int createdVolumeNum) {
        this.createdVolumeNum = createdVolumeNum;
    }

    public Boolean getEnableAutoRenew() {
        return enableAutoRenew;
    }

    public void setEnableAutoRenew(Boolean enableAutoRenew) {
        this.enableAutoRenew = enableAutoRenew;
    }

    public String getRenewTimeUnit() {
        return renewTimeUnit;
    }

    public void setRenewTimeUnit(String renewTimeUnit) {
        this.renewTimeUnit = renewTimeUnit;
    }

    public Integer getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(Integer renewTime) {
        this.renewTime = renewTime;
    }

    @Override
    public String toString() {
        return "VolumeClusterModel{" +
                "clusterId='" + clusterId + '\'' +
                ", clusterName='" + clusterName + '\'' +
                ", productType='" + productType + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", expiredTime='" + expiredTime + '\'' +
                ", status='" + status + '\'' +
                ", clusterType='" + clusterType + '\'' +
                ", logicalZone='" + logicalZone + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", usedCapacity=" + usedCapacity +
                ", availableCapacity=" + availableCapacity +
                ", expandingCapacity=" + expandingCapacity +
                ", createdVolumeNum=" + createdVolumeNum +
                ", enableAutoRenew=" + enableAutoRenew +
                ", renewTimeUnit='" + renewTimeUnit + '\'' +
                ", renewTime=" + renewTime +
                '}';
    }
}
