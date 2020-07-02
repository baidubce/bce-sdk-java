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

import java.util.List;
import com.baidubce.auth.BceCredentials;

/**
 * Request object containing all the options for setting a bucket's CopyrightProtection.
 */
public class SetBucketCopyrightProtectionRequest extends GenericBucketRequest {

    /**
     * The resourceList of Bucket CopyrightProtection.
     */
    private List<String> resource;

    /**
     * The json format for Bucket CopyrightProtection.
     */
    private String jsonBucketCopyrightProtection;

    @Override
    public SetBucketCopyrightProtectionRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }


    @Override
    public SetBucketCopyrightProtectionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }


    /**
     * Constructs a void Constructor for SetBucketCopyrightProtectionRequest.
     */
    public SetBucketCopyrightProtectionRequest() {

    }

    public SetBucketCopyrightProtectionRequest(String bucketName) {
        super(bucketName);
    }

    /**
     * Constructs a new SetBucketCopyrightProtectionRequest object, ready to set the specified Bucket Lifecycle.
     * @param bucketName
     * @param resource The resource of Bucket Lifecycle.
     */
    public SetBucketCopyrightProtectionRequest(String bucketName, List<String> resource) {
        super(bucketName);
        this.resource = resource;
    }

    /**
     * Constructs a SetBucketCopyrightProtectionRequest object, ready to set the specified Bucket CopyrightProtection.
     * @param bucketName
     * @param jsonBucketCopyrightProtection The jsonBucketCopyrightProtection of Bucket Lifecycle.
     */
    public SetBucketCopyrightProtectionRequest(String bucketName, String jsonBucketCopyrightProtection) {
        super(bucketName);
        this.jsonBucketCopyrightProtection = jsonBucketCopyrightProtection;
    }


    public List<String> getResource() {
        return resource;
    }

    public void setResource(List<String> resource) {
        this.resource = resource;
    }

    public String getJsonBucketCopyrightProtection() {
        return jsonBucketCopyrightProtection;
    }

    public void setJsonBucketCopyrightProtection(String jsonBucketCopyrightProtection) {
        this.jsonBucketCopyrightProtection = jsonBucketCopyrightProtection;
    }
}
