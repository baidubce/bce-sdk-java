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
 * Provides options for obtaining the metadata for the specified Baidu Bos object without actually
 * fetching the object contents.
 * This is useful if obtaining only object metadata, and avoids wasting bandwidth from retrieving the object data.
 *
 * <p>
 * The object metadata contains information such as content type, content disposition, etc.,
 * as well as custom user metadata that can be associated with an object in Baidu Bos.
 */
public class GetObjectMetadataRequest extends GenericObjectRequest {

    /**
     * Constructs a new GetObjectMetadataRequest used to retrieve a specified object's metadata.
     *
     * @param bucketName The name of the bucket containing the object whose metadata is being retrieved.
     * @param key The key of the object whose metadata is being retrieved.
     */
    public GetObjectMetadataRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    @Override
    public GetObjectMetadataRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket containing the object whose metadata is being retrieved.
     * Returns this GetObjectMetadataRequest, enabling additional method calls to be chained together.
     *
     * @param bucketName The name of the bucket containing the object's whose metadata is being retrieved.
     * @return This GetObjectMetadataRequest, enabling additional method calls to be chained together.
     */
    @Override
    public GetObjectMetadataRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key of the object whose metadata is being retrieved.
     * Returns this GetObjectMetadataRequest, enabling additional method calls to be chained together.
     *
     * @param key The key of the object whose metadata is being retrieved.
     * @return This GetObjectMetadataRequest, enabling additional method calls to be chained together.
     */
    @Override
    public GetObjectMetadataRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

}
