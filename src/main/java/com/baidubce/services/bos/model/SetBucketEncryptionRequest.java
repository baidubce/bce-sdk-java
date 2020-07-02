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
 * Request object containing all the options for setting a bucket's Encryption.
 */
public class SetBucketEncryptionRequest extends GenericBucketRequest {

    /**
     * The json format for bucketEncryption
     */
    private String jsonBucketEncryption;

    /**
     * The Bucket Encryption
     */
    private BucketEncryption bucketEncryption;

    /**
     * Constructs a void Constructor for SetBucketEncryptionRequest
     */
    public SetBucketEncryptionRequest() {

    }

    public SetBucketEncryptionRequest(String bucketName) {
        super(bucketName);
    }

    public SetBucketEncryptionRequest(String bucketName, BucketEncryption bucketEncryption) {
        super(bucketName);
        this.bucketEncryption = bucketEncryption;
    }

    public SetBucketEncryptionRequest(String bucketName, String jsonBucketEncryption) {
        super(bucketName);
        this.jsonBucketEncryption = jsonBucketEncryption;
    }


    public String getJsonBucketEncryption() {
        return jsonBucketEncryption;
    }

    public void setJsonBucketEncryption(String jsonBucketEncryption) {
        this.jsonBucketEncryption = jsonBucketEncryption;
    }

    public SetBucketEncryptionRequest withJsonBucketEncryption(String jsonBucketEncryption) {
        this.setJsonBucketEncryption(jsonBucketEncryption);
        return this;
    }

    public BucketEncryption getBucketEncryption() {
        return bucketEncryption;
    }

    public void setBucketEncryption(BucketEncryption bucketEncryption) {
        this.bucketEncryption = bucketEncryption;
    }

    public SetBucketEncryptionRequest withBucketEncryption(BucketEncryption bucketEncryption) {
        setBucketEncryption(bucketEncryption);
        return this;
    }

    @Override
    public SetBucketEncryptionRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public SetBucketEncryptionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
