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
package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.google.common.base.Preconditions;

/**
 * Request object for rename Bos object's key
 */
public class RenameObjectRequest extends GenericObjectRequest {

    /**
     *  The origin key of object
     */
    private String sourceKey;

    /**
     * Constructs a new RenameObjectRequest object to rename Bos object
     * specified bucket, sourceKey and key.
     *
     * @param bucketName The name of an existing bucket to which the new object will be uploaded.
     * @param sourceKey The origin key of object
     * @param key The destination key of object
     */
    public RenameObjectRequest(String bucketName, String sourceKey, String key) {
        super(bucketName, key);
        this.setSourceKey(sourceKey);
    }

    /**
     * Get the object origin key
     *
     * @return The origin Key
     */
    public String getSourceKey() {
        return this.sourceKey;
    }

    /**
     * Set the sourceKey of the Request
     *
     * @param sourceKey the key of Baidu bos object
     */
    public void setSourceKey(String sourceKey) {
        Preconditions.checkNotNull(sourceKey, "sourceKey should not be null");
        this.sourceKey = sourceKey;
    }

    /**
     * Set the sourceKey of the Request
     * Returns this RenameObjectRequest, enabling additional method calls to be chained together.
     *
     * @param sourceKey the key of Baidu bos object
     * @return This RenameObjectRequest, enabling additional method calls to be chained together.
     */
    public RenameObjectRequest withSourceKey(String sourceKey) {
        this.setSourceKey(sourceKey);
        return this;
    }

    @Override
    public RenameObjectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket where this request will upload a new
     * object to. Returns this object, enabling additional method calls to be
     * chained together.
     *
     * @param bucketName The name of an existing bucket where this request will upload a new object to.
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public RenameObjectRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key under which to store the new object. Returns this object,
     * enabling additional method calls to be chained together.
     *
     * @param key The key under which to store the new object.
     * @return This PutObjectRequest, enabling additional method calls to be chained together.
     */
    @Override
    public RenameObjectRequest withKey(String key) {
        this.setKey(key);
        return this;
    }
}
