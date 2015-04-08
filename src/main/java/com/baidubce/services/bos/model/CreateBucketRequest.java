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
 * Provides options for creating an Baidu Bos bucket.
 */
public class CreateBucketRequest extends GenericBucketRequest {

    /**
     * Constructs a new {@link CreateBucketRequest},ready to be executed to create the specified bucket.
     *
     * @param bucketName The name of the Baidu Bos bucket to create.
     */
    public CreateBucketRequest(String bucketName) {
        super(bucketName);
    }

    public CreateBucketRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public CreateBucketRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }
}
