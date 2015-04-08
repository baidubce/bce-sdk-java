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

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class GenericUploadRequest extends GenericObjectRequest {

    /**
     * The ID of an existing, initiated multipart upload, with which this new
     * part will be associated.
     */
    private String uploadId;

    public GenericUploadRequest() {
        super();
    }

    public GenericUploadRequest(String bucketName, String key, String uploadId) {
        super(bucketName, key);
        this.setUploadId(uploadId);
    }

    /**
     * Returns the ID of the existing, initiated multipart upload with which
     * this new part will be associated.
     *
     * @return the ID of the existing, initiated multipart upload with which
     * this new part will be associated.
     */
    public String getUploadId() {
        return this.uploadId;
    }

    /**
     * Sets the ID of the existing, initiated multipart upload with which this
     * new part will be associated.
     *
     * @param uploadId the ID of the existing, initiated multipart upload with which
     *                 this new part will be associated.
     */
    public void setUploadId(String uploadId) {
        checkNotNull(uploadId, "uploadId should not be null.");
        this.uploadId = uploadId;
    }

    public abstract GenericUploadRequest withUploadId(String uploadId);
}
