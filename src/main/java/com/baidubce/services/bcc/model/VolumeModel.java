/*
 * Copyright (c) 2014-2019 Baidu.com, Inc. All Rights Reserved
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
import java.util.List;

/**
 * volume detail info model
 */
public class VolumeModel {

    /**
     * The id of the volume
     */
    private String id;

    /**
     * The name of the volume
     */
    private String name;

    /**
     * The total size of the volume in GB.
     */
    private int diskSizeInGB;

    /**
     * The payment method of purchasing the volume,
     * see more detail in <a href = "https://bce.baidu.com/doc/BCC/API.html#Billing">BCE API doc</a>
     */
    private String paymentTiming;

    /**
     * The time when the volume was created.
     */
    private Date createTime;

    /**
     * The time when the volume will be expired.
     * If it's Postpaid, it will not have expired time.
     */
    private Date expireTime;

    /**
     * The status of the volume,
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#VolumeStatus">BCE API doc</a>
     */
    private String status;

    /**
     * The type to describe the volume .
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#VolumeType">BCE API doc</a>
     */
    private String type;

    /**
     * The storage type of volume, see more detail in
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#StorageType">BCE API doc</a>
     */
    private String storageType;

    /**
     * The description of the volume.
     */
    private String desc;

    /**
     * The list of detail info to describe where the volume will attach to.
     * If the volume has not been mounted, it will be null.
     */
    private List<VolumeAttachmentModel> attachments;

    /**
     * the name of available zone
     */
    private String zoneName;

    /**
     * The id of region
     */
    private String regionId;

    /**
     * The count of snapshots on the disk
     */
    private String snapshotNum;

    /**
     * The snapshotId for creating the volume
     */
    private String sourceSnapshotId;

    /**
     * The snapshotPolicy of the volume
     */
    private AutoSnapshotPolicyModel autoSnapshotPolicy;

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

    public int getDiskSizeInGB() {
        return diskSizeInGB;
    }

    public void setDiskSizeInGB(int diskSizeInGB) {
        this.diskSizeInGB = diskSizeInGB;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<VolumeAttachmentModel> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<VolumeAttachmentModel> attachments) {
        this.attachments = attachments;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSnapshotNum() {
        return snapshotNum;
    }

    public void setSnapshotNum(String snapshotNum) {
        this.snapshotNum = snapshotNum;
    }

    public String getSourceSnapshotId() {
        return sourceSnapshotId;
    }

    public void setSourceSnapshotId(String sourceSnapshotId) {
        this.sourceSnapshotId = sourceSnapshotId;
    }

    public AutoSnapshotPolicyModel getAutoSnapshotPolicy() {
        return autoSnapshotPolicy;
    }

    public void setAutoSnapshotPolicy(AutoSnapshotPolicyModel autoSnapshotPolicy) {
        this.autoSnapshotPolicy = autoSnapshotPolicy;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "VolumeModel{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", diskSizeInGB=" + diskSizeInGB
                + ", paymentTiming='" + paymentTiming + '\''
                + ", createTime=" + createTime
                + ", expireTime=" + expireTime
                + ", status='" + status + '\''
                + ", type='" + type + '\''
                + ", storageType='" + storageType + '\''
                + ", desc='" + desc + '\''
                + ", attachments=" + attachments
                + ", zoneName='" + zoneName + '\''
                + ", regionId='" + regionId + '\''
                + ", snapshotNum='" + snapshotNum + '\''
                + ", sourceSnapshotId='" + sourceSnapshotId + '\''
                + ", autoSnapshotPolicy=" + autoSnapshotPolicy
                + ", tags=" + tags
                + '}';
    }
}
