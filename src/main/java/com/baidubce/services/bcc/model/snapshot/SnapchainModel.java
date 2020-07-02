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
package com.baidubce.services.bcc.model.snapshot;

/**
 * The model of snap chain.
 */
public class SnapchainModel {

    private String status;

    private String chainSize;
    private String chainId;
    private String instanceId;

    private String userId;

    private String volumeId;

    private int volumeSize;

    private int manualSnapCount;

    private int autoSnapCount;

    private String createTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChainSize() {
        return chainSize;
    }

    public void setChainSize(String chainSize) {
        this.chainSize = chainSize;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public int getVolumeSize() {
        return volumeSize;
    }

    public void setVolumeSize(int volumeSize) {
        this.volumeSize = volumeSize;
    }

    public int getManualSnapCount() {
        return manualSnapCount;
    }

    public void setManualSnapCount(int manualSnapCount) {
        this.manualSnapCount = manualSnapCount;
    }

    public int getAutoSnapCount() {
        return autoSnapCount;
    }

    public void setAutoSnapCount(int autoSnapCount) {
        this.autoSnapCount = autoSnapCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SnapchainModel{" +
                "status='" + status + '\'' +
                ", chainSize='" + chainSize + '\'' +
                ", chainId='" + chainId + '\'' +
                ", instanceId='" + instanceId + '\'' +
                ", userId='" + userId + '\'' +
                ", volumeId='" + volumeId + '\'' +
                ", volumeSize=" + volumeSize +
                ", manualSnapCount=" + manualSnapCount +
                ", autoSnapCount=" + autoSnapCount +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
