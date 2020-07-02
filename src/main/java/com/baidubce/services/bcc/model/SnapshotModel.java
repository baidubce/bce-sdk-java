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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * The detail model of the snapshot.
 */
public class SnapshotModel {

    /**
     * The id of the snapshot.
     */
    private String id;

    /**
     * The name of the snapshot.
     */
    private String name;

    /**
     * The size of the snapshot in GB
     */
    private int sizeInGB;

    /**
     * The time when the snapshot was created.
     */
    private Date createTime;

    /**
     * The status of the snapshot.
     * see more detail on <a href = "https://bce.baidu.com/doc/BCC/API.html#SnapshotStatus">BCE API doc</>
     */
    private String status;

    /**
     * The method to describe how the snapshot was created, available values are "MANUAL/MIGRATION/auto".
     * When the snapshot was created manually,the createMethod will be "MANUAL";
     * When the snapshot was migrated from other,the createMethod will be "MIGRATION";
     * When the snapshot was created by time schedule task,the createMethod will be "auto";
     */
    private String createMethod;

    /**
     * The id of the volume.
     */
    private String volumeId;

    /**
     * The description of the snapshot.
     */
    private String desc;

    /**
     * The time when the snapshot will be expired.
     */
    private Date expireTime;

    /**
     * The snapId of the instance.
     */
    private String insnapId;

    /**
     * Whether the image is big or not. The default value is false. If <code>true</code>, it means it is the big image.
     */
    @JsonProperty("package")
    private boolean packaged;

    /**
     * The id of image which is defined by user.
     */
    @JsonProperty("templateId")
    private String imageId;

    /**
     * Whether the snapshot is encrypted.
     */
    private boolean encrypted;

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

    public int getSizeInGB() {
        return sizeInGB;
    }

    public void setSizeInGB(int sizeInGB) {
        this.sizeInGB = sizeInGB;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateMethod() {
        return createMethod;
    }

    public void setCreateMethod(String createMethod) {
        this.createMethod = createMethod;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getInsnapId() {
        return insnapId;
    }

    public void setInsnapId(String insnapId) {
        this.insnapId = insnapId;
    }

    public boolean isPackaged() {
        return packaged;
    }

    public void setPackaged(boolean packaged) {
        this.packaged = packaged;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    @Override
    public String toString() {
        return "SnapshotModel{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", sizeInGB=" + sizeInGB
                + ", createTime='" + createTime + '\''
                + ", status='" + status + '\''
                + ", createMethod='" + createMethod + '\''
                + ", volumeId='" + volumeId + '\''
                + ", desc='" + desc + '\''
                + ", expireTime='" + expireTime + '\''
                + ", insnapId='" + insnapId + '\''
                + ", package='" + packaged + '\''
                + ", templateId='" + imageId + '\''
                + ", encrypted='" + encrypted + '\''
                + '}';
    }
}
