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
 * Provides options for obtaining the metadata for the specified Baidu Bos object without actually
 * fetching the object contents.
 * This is useful if obtaining only object metadata, and avoids wasting bandwidth from retrieving the object data.
 *
 * <p>
 * The object metadata contains information such as content type, content disposition, etc.,
 * as well as custom user metadata that can be associated with an object in Baidu Bos.
 */
public class GetObjectMetadataRequest extends GenericObjectRequest {

    private String ifNoneMatch = null;

    private String ifMatch = null;

    private String ifUnmodifiedSince = null;

    private String ifModifiedSince = null;

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

    public String getIfNoneMatch() {
        return ifNoneMatch;
    }

    public void setIfNoneMatch(String ifNoneMatch) {
        this.ifNoneMatch = ifNoneMatch;
    }

    public String getIfMatch() {
        return ifMatch;
    }

    public void setIfMatch(String ifMatch) {
        this.ifMatch = ifMatch;
    }

    public String getIfUnmodifiedSince() {
        return ifUnmodifiedSince;
    }

    public void setIfUnmodifiedSince(String ifUnmodifiedSince) {
        this.ifUnmodifiedSince = ifUnmodifiedSince;
    }

    public String getIfModifiedSince() {
        return ifModifiedSince;
    }

    public void setIfModifiedSince(String ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
    }

}
