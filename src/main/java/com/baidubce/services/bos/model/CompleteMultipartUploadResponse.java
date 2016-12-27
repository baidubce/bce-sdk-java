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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CompleteMultipartUploadResponse contains all the information about the CompleteMultipartUpload method.
 */
public class CompleteMultipartUploadResponse extends BosResponse {

    /**
     * The name of the bucket containing the completed multipart upload.
     */
    private String bucketName;

    /**
     * The key by which the object is stored.
     */
    private String key;

    /**
     * The URL identifying the new multipart object.
     */
    private String location;

    /**
     * The entity tag identifying the new object. An entity tag is an opaque
     * string that changes if and only if an object's data changes.
     */
    private String eTag;

    /**
     * Returns the URL identifying the new multipart object.
     *
     * @return The URL identifying the new multipart object.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Sets the URL identifying the new multipart object.
     *
     * @param location The URL identifying the new multipart object.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the name of the bucket containing the completed multipart object.
     *
     * @return The name of the bucket containing the completed multipart object.
     */
    public String getBucketName() {
        return this.bucketName;
    }

    /**
     * Sets the name of the bucket containing the completed multipart object.
     *
     * @param bucketName The name of the bucket containing the completed multipart object.
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Gets the key by which the newly created object is stored.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Sets the key of the newly created object.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the entity tag identifying the new object. An entity tag is an
     * opaque string that changes if and only if an object's data changes.
     *
     * @return An opaque string that changes if and only if an object's data changes.
     */
    public String getETag() {
        return this.eTag;
    }

    /**
     * Sets the entity tag identifying the new object. An entity tag is an
     * opaque string that changes if and only if an object's data changes.
     *
     * @param eTag The entity tag.
     */
    @JsonProperty("eTag")
    public void setETag(String eTag) {
        this.eTag = eTag;
    }
}
