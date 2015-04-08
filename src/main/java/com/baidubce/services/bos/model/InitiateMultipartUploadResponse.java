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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains the results of initiating a multipart upload, particularly the unique ID of the new multipart upload.
 */
public class InitiateMultipartUploadResponse extends BosResponse {

    /**
     * The name of the bucket in which the new multipart upload was initiated.
     */
    private String bucketName;

    /**
     * The object key for which the multipart upload was initiated.
     */
    private String key;

    /**
     * The unique ID of the new multipart upload.
     */
    private String uploadId;

    /**
     * Returns the name of the bucket in which the new multipart upload was
     * initiated.
     *
     * @return The name of the bucket in which the new multipart upload was initiated.
     */
    public String getBucketName() {
        return this.bucketName;
    }

    /**
     * Sets the name of the bucket in which the new multipart upload was
     * initiated.
     *
     * @param bucketName The name of the bucket in which the new multipart upload was initiated.
     */
    @JsonProperty("bucket")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Returns the object key for which the multipart upload was initiated.
     *
     * @return The object key for which the multipart upload was initiated.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the object key for which the multipart upload was initiated.
     *
     * @param key The object key for which the multipart upload was initiated.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the initiated multipart upload ID.
     *
     * @return the initiated multipart upload ID.
     */
    public String getUploadId() {
        return this.uploadId;
    }

    /**
     * Sets the initiated multipart upload ID.
     *
     * @param uploadId The initiated multipart upload ID.
     */
    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
