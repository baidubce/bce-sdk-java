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

import com.baidubce.auth.BceCredentials;

/**
 * The AbortMultipartUploadRequest contains the parameters used for the AbortMultipartUpload method.
 *
 * <p>
 * Required Parameters: BucketName, Key, UploadId
 */
public class AbortMultipartUploadRequest extends GenericUploadRequest {

    /**
     * Constructs a new request to abort a multipart upload.
     *
     * @param bucketName The name of the bucket containing the multipart upload to abort.
     * @param key The key of the multipart upload to abort.
     * @param uploadId The ID of the multipart upload to abort.
     */
    public AbortMultipartUploadRequest(String bucketName, String key, String uploadId) {
        super(bucketName, key, uploadId);
    }


    @Override
    public AbortMultipartUploadRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket containing the multipart upload to abort and returns the updated object
     * so that additional calls can be chained together.
     *
     * @param bucketName The name of the bucket containing the multipart upload to abort.
     * @return The updated AbortMultipartUploadRequest object.
     */
    @Override
    public AbortMultipartUploadRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key of the multipart upload to abort and returns the updated AbortMultipartUploadRequest object
     * so that additional method calls can be chained together.
     *
     * @param key The key of the multipart upload to abort.
     * @return The updated AbortMultipartUploadRequest.
     */
    @Override
    public AbortMultipartUploadRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Sets the ID of the multipart upload to abort, and returns this updated AbortMultipartUploadRequest object
     * so that additional method calls can be chained together.
     *
     * @param uploadId The ID of the multipart upload to abort.
     * @return The updated AbortMultipartUploadRequest object.
     */
    @Override
    public AbortMultipartUploadRequest withUploadId(String uploadId) {
        this.setUploadId(uploadId);
        return this;
    }

}
