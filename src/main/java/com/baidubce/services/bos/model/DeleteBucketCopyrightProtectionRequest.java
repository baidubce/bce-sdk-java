/*
 * Copyright 2014-2019 Baidu, Inc.
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
 * Provides options for deleting a Bucket CopyrightProtection
 */
public class DeleteBucketCopyrightProtectionRequest extends GenericBucketRequest {
    /**
     * Constructs a void constructor for DeleteBucketCopyrightProtectionRequest.
     */
    public DeleteBucketCopyrightProtectionRequest() {

    }

    /**
     * Constructs a DeleteBucketCopyrightProtectionRequest, ready to be executed to delete the specified Bucket
     * CopyrightProtection.
     * @param bucketName The name of the Baidu Bos bucket to delete Bucket CopyrightProtection.
     */
    public DeleteBucketCopyrightProtectionRequest(String bucketName) {
        super(bucketName);
    }

    /**
     * Sets the name of the Baidu Bos bucket to delete Bucket CopyrightProtection.
     * @param bucketName The name of the Baidu Bos bucket to delete Bucket CopyrightProtection.
     */
    @Override
    public DeleteBucketCopyrightProtectionRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public DeleteBucketCopyrightProtectionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
