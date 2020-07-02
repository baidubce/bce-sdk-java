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

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcc.model.VpcInfo;
import com.baidubce.services.as.model.zone.ZoneInfo;

import java.util.List;

/**
 * The response of getting the asGroup.
 */
public class GetAsGroupResponse extends AbstractBceResponse {
    private String groupId;
    private String groupName;
    private String region;
    private AsGroupStatus status;
    private VpcInfo vpcInfo;
    private String rdsIds;
    private String scsIds;
    private String createTime;
    private List<ZoneInfo> zoneInfo;
    private GroupConfig groupConfig;
    private String blbId;
    private int nodeNum;

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

    public VpcInfo getVpcInfo() {
        return vpcInfo;
    }

    public void setVpcInfo(VpcInfo vpcInfo) {
        this.vpcInfo = vpcInfo;
    }

    public String getRdsIds() {
        return rdsIds;
    }

    public void setRdsIds(String rdsIds) {
        this.rdsIds = rdsIds;
    }

    public String getScsIds() {
        return scsIds;
    }

    public void setScsIds(String scsIds) {
        this.scsIds = scsIds;
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

    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    @Override
    public String toString() {
        return "GetAsGroupResponse{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", region='" + region + '\'' +
                ", status=" + status +
                ", vpcInfo=" + vpcInfo +
                ", rdsIds='" + rdsIds + '\'' +
                ", scsIds='" + scsIds + '\'' +
                ", createTime='" + createTime + '\'' +
                ", zoneInfo=" + zoneInfo +
                ", groupConfig=" + groupConfig +
                ", blbId='" + blbId + '\'' +
                ", nodeNum=" + nodeNum +
                '}';
    }
}
