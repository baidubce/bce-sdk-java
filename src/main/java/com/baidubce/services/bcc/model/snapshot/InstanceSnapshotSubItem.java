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

import com.baidubce.services.bcc.model.TagModel;

import java.util.List;

/**
 * The detail model of the sub snapshot inside an instance snapshot(consistent snapshot group).
 */
public class InstanceSnapshotSubItem {

    /**
     * The id of the sub snapshot.
     */
    private String snapshotId;

    /**
     * The name of the sub snapshot.
     */
    private String snapshotName;

    /**
     * The size of the sub snapshot in GiB.
     */
    private Integer snapshotSizeInGiB;

    /**
     * The status of the sub snapshot.
     * <p>
     * NOTE: the available values are defined by {@link SnapshotStatus}, which is a different enum from
     * {@link InstanceSnapshotStatus} used by the instance snapshot group.
     */
    private String snapshotStatus;

    /**
     * The id of the volume which the sub snapshot was created from.
     */
    private String volumeId;

    /**
     * The id of the instance snapshot(consistent snapshot group) which the sub snapshot belongs to.
     */
    private String instanceSnapshotId;

    /**
     * The method to describe how the sub snapshot was created.
     * The available values are defined by {@link SnapshotCreationMethod}.
     */
    private String creationMethod;

    /**
     * The description of the sub snapshot.
     */
    private String description;

    /**
     * The time when the sub snapshot was created.
     */
    private String createdTime;

    /**
     * The time when the sub snapshot will be expired.
     */
    private String expiredTime;

    /**
     * Whether the sub snapshot is encrypted.
     */
    private Boolean encrypted;

    /**
     * The id of the image which was created from the sub snapshot.
     */
    private String imageId;

    /**
     * Whether the data volumes are included.
     * <p>
     * NOTE: this field is declared by the server side but never filled, so it is always null.
     * This interface does not return it for now.
     */
    private Boolean includeDataVolumes;

    /**
     * The progress of creating the sub snapshot.
     * <p>
     * NOTE: this field is declared by the server side but never filled, so it is always null.
     * This interface does not return it for now.
     */
    private String progress;

    /**
     * The tags bound to the sub snapshot.
     * <p>
     * NOTE: this field is declared by the server side but never filled, so it is always null.
     * This interface does not return it for now.
     */
    private List<TagModel> tags;

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public String getSnapshotName() {
        return snapshotName;
    }

    public void setSnapshotName(String snapshotName) {
        this.snapshotName = snapshotName;
    }

    public Integer getSnapshotSizeInGiB() {
        return snapshotSizeInGiB;
    }

    public void setSnapshotSizeInGiB(Integer snapshotSizeInGiB) {
        this.snapshotSizeInGiB = snapshotSizeInGiB;
    }

    public String getSnapshotStatus() {
        return snapshotStatus;
    }

    public void setSnapshotStatus(String snapshotStatus) {
        this.snapshotStatus = snapshotStatus;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getInstanceSnapshotId() {
        return instanceSnapshotId;
    }

    public void setInstanceSnapshotId(String instanceSnapshotId) {
        this.instanceSnapshotId = instanceSnapshotId;
    }

    public String getCreationMethod() {
        return creationMethod;
    }

    public void setCreationMethod(String creationMethod) {
        this.creationMethod = creationMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Boolean getIncludeDataVolumes() {
        return includeDataVolumes;
    }

    public void setIncludeDataVolumes(Boolean includeDataVolumes) {
        this.includeDataVolumes = includeDataVolumes;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "InstanceSnapshotSubItem{"
                + "snapshotId='" + snapshotId + '\''
                + ", snapshotName='" + snapshotName + '\''
                + ", snapshotSizeInGiB=" + snapshotSizeInGiB
                + ", snapshotStatus='" + snapshotStatus + '\''
                + ", volumeId='" + volumeId + '\''
                + ", instanceSnapshotId='" + instanceSnapshotId + '\''
                + ", creationMethod='" + creationMethod + '\''
                + ", description='" + description + '\''
                + ", createdTime='" + createdTime + '\''
                + ", expiredTime='" + expiredTime + '\''
                + ", encrypted=" + encrypted
                + ", imageId='" + imageId + '\''
                + ", includeDataVolumes=" + includeDataVolumes
                + ", progress='" + progress + '\''
                + ", tags=" + tags
                + '}';
    }
}
