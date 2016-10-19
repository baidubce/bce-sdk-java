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
package com.baidubce.services.bos.model;

import java.util.Date;

import com.baidubce.model.User;

/**
 * A multipart upload is an upload to Baidu Bos that is creating by uploading
 * individual pieces of an object, then telling Baidu Bos to complete the
 * multipart upload and concatenate all the individual pieces together into a
 * single object.
 */
public class MultipartUploadSummary {

    /**
     * The key by which this upload is stored.
     */
    private String key;

    /**
     * The unique ID of this multipart upload.
     */
    private String uploadId;

    /**
     * The owner of this multipart upload.
     */
    private User owner;

    /**
     * The date at which this upload was initiated.
     */
    private Date initiated;

    /**
     * The StorageClass is an identification that distinguish between infrequent access bos 
     * and standard bos. 
     */
    private String storageClass;

    /**
     * Returns the key by which this upload is stored.
     *
     * @return The key by which this upload is stored.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key by which this upload is stored.
     *
     * @param key The key by which this upload is stored.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the unique ID of this multipart upload.
     *
     * @return The unique ID of this multipart upload.
     */
    public String getUploadId() {
        return this.uploadId;
    }

    /**
     * Sets the unique ID of this multipart upload.
     *
     * @param uploadId The unique ID of this multipart upload.
     */
    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    /**
     * Returns the owner of this multipart upload.
     *
     * @return The owner of this multipart upload.
     */
    public User getOwner() {
        return this.owner;
    }

    /**
     * Sets the owner of this multipart upload.
     *
     * @param owner The owner of this multipart upload.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Returns the date at which this upload was initiated.
     *
     * @return The date at which this upload was initiated.
     */
    public Date getInitiated() {
        return this.initiated;
    }

    /**
     * Sets the date at which this upload was initiated.
     *
     * @param initiated The date at which this upload was initiated.
     */
    public void setInitiated(Date initiated) {
        this.initiated = initiated;
    }

    /**
     * Return the storageClass.
     * 
     * @return storageClass The StorageClass is an identification that distinguish between
     * infrequent access bos and standard bos.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storageClass.
     * 
     * @param storageClass The StorageClass is an identification that distinguish between
     * infrequent access bos and standard bos.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }
}
