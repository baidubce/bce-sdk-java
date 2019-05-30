/*
 * Copyright 2014 Baidu, Inc.
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
 * Provides options for deleting a Bucket Lifecycle.
 */
public class DeleteBucketLifecycleRequest extends GenericBucketRequest {

    /**
     * Constructs a void constructor for DeleteBucketLifecycleRequest.
     */
    public DeleteBucketLifecycleRequest() {

    }

    /**
     * Constructs a new DeleteBucketLifecycleRequest, ready to be executed to delete the specified Bucket Lifecycle.
     * @param bucketName The name of the Baidu Bos bucket to delete Bucket Lifecycle.
     */
    public DeleteBucketLifecycleRequest(String bucketName) {
        super(bucketName);
    }

    public DeleteBucketLifecycleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the Baidu Bos bucket to delete Bucket Lifecycle.
     * @param bucketName The name of the Baidu Bos bucket to delete Bucket Lifecycle.
     */
    public DeleteBucketLifecycleRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }
}