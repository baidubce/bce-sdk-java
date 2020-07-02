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
package com.baidubce.services.as.model.asgroup;

import com.baidubce.services.as.model.zone.ZoneInfo;

import java.util.List;

/**
 * The information of the asGroup.
 */
public class AsGroup {
    /**
     * The id of specified auto scaling group.
     */
    private String groupId;

    /**
     * The name of specified auto scaling group.
     */
    private String groupName;

    /**
     * The region where the auto scaling group is located
     */
    private String region;

    /**
     * Current status of the auto scaling group
     */
    private AsGroupStatus status;

    /**
     * The if of vpc
     */
    private String vpcId;

    /**
     * Number of nodes in the auto scaling group
     */
    private int nodeNum;


    private String createTime;

    private List<ZoneInfo> zoneInfo;

    /**
     * The spec of auto scaling group
     */
    private GroupConfig groupConfig;

    private String blbId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public AsGroupStatus getStatus() {
        return status;
    }

    public void setStatus(AsGroupStatus status) {
        this.status = status;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ZoneInfo> getZoneInfo() {
        return zoneInfo;
    }

    public void setZoneInfo(List<ZoneInfo> zoneInfo) {
        this.zoneInfo = zoneInfo;
    }

    public GroupConfig getGroupConfig() {
        return groupConfig;
    }

    public void setGroupConfig(GroupConfig groupConfig) {
        this.groupConfig = groupConfig;
    }

    public String getBlbId() {
        return blbId;
    }

    public void setBlbId(String blbId) {
        this.blbId = blbId;
    }

    @Override
    public String toString() {
        return "AsGroup{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", region='" + region + '\'' +
                ", status=" + status +
                ", vpcId='" + vpcId + '\'' +
                ", nodeNum=" + nodeNum +
                ", createTime='" + createTime + '\'' +
                ", zoneInfo=" + zoneInfo +
                ", groupConfig=" + groupConfig +
                ", blbId='" + blbId + '\'' +
                '}';
    }
}
