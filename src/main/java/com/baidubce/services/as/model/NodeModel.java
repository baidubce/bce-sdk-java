/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.as.model;

import com.baidubce.services.as.model.asgroup.TagInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class NodeModel {
    private String instanceId;
    private String instanceName;
    private String floatingIp;
    private String internalIp;
    private String status;
    private String payment;
    private int cpuCount;
    private int memoryCapacityInGB;
    private String instanceType;
    private int sysDiskInGB;
    private String subnetType;
    @JsonProperty("isProtected")
    private boolean protect;
    private String createTime;
    private String nodeType;
    private AsEipModel eip;
    private List<TagInfo> tags;
    private String groupId;
    private boolean isManaged;

    public List<TagInfo> getTags() {
        return tags;
    }

    public void setTags(List<TagInfo> tags) {
        this.tags = tags;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isManaged() {
        return isManaged;
    }

    public void setManaged(boolean managed) {
        isManaged = managed;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getFloatingIp() {
        return floatingIp;
    }

    public void setFloatingIp(String floatingIp) {
        this.floatingIp = floatingIp;
    }

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public int getSysDiskInGB() {
        return sysDiskInGB;
    }

    public void setSysDiskInGB(int sysDiskInGB) {
        this.sysDiskInGB = sysDiskInGB;
    }

    public String getSubnetType() {
        return subnetType;
    }

    public void setSubnetType(String subnetType) {
        this.subnetType = subnetType;
    }

    public boolean isProtect() {
        return protect;
    }

    public void setProtect(boolean protect) {
        this.protect = protect;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public AsEipModel getEip() {
        return eip;
    }

    public void setEip(AsEipModel eip) {
        this.eip = eip;
    }

    @Override
    public String toString() {
        return "NodeModel{" +
                "instanceId='" + instanceId + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", floatingIp='" + floatingIp + '\'' +
                ", internalIp='" + internalIp + '\'' +
                ", status='" + status + '\'' +
                ", payment='" + payment + '\'' +
                ", cpuCount=" + cpuCount +
                ", memoryCapacityInGB=" + memoryCapacityInGB +
                ", instanceType='" + instanceType + '\'' +
                ", sysDiskInGB=" + sysDiskInGB +
                ", subnetType='" + subnetType + '\'' +
                ", protect=" + protect +
                ", createTime='" + createTime + '\'' +
                ", nodeType='" + nodeType + '\'' +
                ", eip=" + eip +
                ", tags=" + tags +
                ", groupId='" + groupId + '\'' +
                ", isManaged=" + isManaged +
                '}';
    }
}
