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

import java.util.List;

/**
 * The detail model of the instance snapshot(consistent snapshot group).
 */
public class InstanceSnapshotModel {

    /**
     * The id of the instance snapshot.
     */
    private String instanceSnapshotId;

    /**
     * The name of the instance snapshot.
     */
    private String instanceSnapshotName;

    /**
     * The total size of the instance snapshot in GiB.
     */
    private Integer instanceSnapshotSizeInGiB;

    /**
     * The time when the instance snapshot was created.
     */
    private String createdTime;

    /**
     * The status of the instance snapshot group.
     * The available values are defined by {@link InstanceSnapshotStatus}.
     */
    private String instanceSnapshotStatus;

    /**
     * The id of the instance which the instance snapshot was created from.
     */
    private String instanceId;

    /**
     * The sub snapshots inside the instance snapshot.
     */
    private List<InstanceSnapshotSubItem> snapshots;

    public String getInstanceSnapshotId() {
        return instanceSnapshotId;
    }

    public void setInstanceSnapshotId(String instanceSnapshotId) {
        this.instanceSnapshotId = instanceSnapshotId;
    }

    public String getInstanceSnapshotName() {
        return instanceSnapshotName;
    }

    public void setInstanceSnapshotName(String instanceSnapshotName) {
        this.instanceSnapshotName = instanceSnapshotName;
    }

    public Integer getInstanceSnapshotSizeInGiB() {
        return instanceSnapshotSizeInGiB;
    }

    public void setInstanceSnapshotSizeInGiB(Integer instanceSnapshotSizeInGiB) {
        this.instanceSnapshotSizeInGiB = instanceSnapshotSizeInGiB;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getInstanceSnapshotStatus() {
        return instanceSnapshotStatus;
    }

    public void setInstanceSnapshotStatus(String instanceSnapshotStatus) {
        this.instanceSnapshotStatus = instanceSnapshotStatus;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<InstanceSnapshotSubItem> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<InstanceSnapshotSubItem> snapshots) {
        this.snapshots = snapshots;
    }

    @Override
    public String toString() {
        return "InstanceSnapshotModel{"
                + "instanceSnapshotId='" + instanceSnapshotId + '\''
                + ", instanceSnapshotName='" + instanceSnapshotName + '\''
                + ", instanceSnapshotSizeInGiB=" + instanceSnapshotSizeInGiB
                + ", createdTime='" + createdTime + '\''
                + ", instanceSnapshotStatus='" + instanceSnapshotStatus + '\''
                + ", instanceId='" + instanceId + '\''
                + ", snapshots=" + snapshots
                + '}';
    }
}
