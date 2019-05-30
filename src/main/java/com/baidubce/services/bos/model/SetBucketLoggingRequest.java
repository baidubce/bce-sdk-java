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
 * Request object containing all the options for setting a bucket's Logging.
 */
public class SetBucketLoggingRequest extends GenericBucketRequest {

    /**
     * The targetBucket for Bucket Logging.
     */
    private String targetBucket;

    /**
     * The targetPrefix for Bucket Logging.
     */
    private String targetPrefix;

    /**
     * The json format Logging for Bucket Logging.
     */
    private String jsonPutBucketLogging;

    public SetBucketLoggingRequest() {

    }

    /**
     * Constructs a new SetBucketLoggingRequest object, ready to set the specified Bucket Logging.
     * @param bucketName
     * @param targetBucket The targetBucket of Bucket Logging.
     * @param targetPrefix The targetPrefix of Bucket Logging.
     */
    public SetBucketLoggingRequest(String bucketName, String targetBucket, String targetPrefix) {
        super(bucketName);
        this.targetBucket = targetBucket;
        this.targetPrefix = targetPrefix;
    }

    /**
     * Constructs a new SetBucketLoggingRequest object, ready to set the specified Bucket Logging.
     * @param bucketName
     * @param jsonPutBucketLogging The jsonPutBucketLogging of Bucket Logging.
     */
    public SetBucketLoggingRequest(String bucketName, String jsonPutBucketLogging) {
        super(bucketName);
        this.jsonPutBucketLogging = jsonPutBucketLogging;
    }

    @Override
    public SetBucketLoggingRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public SetBucketLoggingRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Gets the targetBucket of Bucket Logging.
     * @return the targetBucket of Bucket Logging.
     */
    public String getTargetBucket() {
        return targetBucket;
    }

    /**
     * Sets the targetBucket of Bucket Logging.
     * @param targetBucket The targetBucket of Bucket Logging.
     */
    public void setTargetBucket(String targetBucket) {
        this.targetBucket = targetBucket;
    }

    /**
     * Gets the targetPrefix of Bucket Logging.
     * @return the targetPrefix of Bucket Logging.
     */
    public String getTargetPrefix() {
        return targetPrefix;
    }

    /**
     * Sets the targetPrefix of Bucket Logging.
     * @param targetPrefix The targetBucket of Bucket Logging.
     */
    public void setTargetPrefix(String targetPrefix) {
        this.targetPrefix = targetPrefix;
    }

    /**
     * Gets the jsonPutBucketLogging of Bucket Logging.
     * @return the jsonPutBucketLogging of Bucket Logging.
     */
    public String getJsonPutBucketLogging() {
        return jsonPutBucketLogging;
    }

    /**
     * Sets the jsonPutBucketLogging of Bucket Logging.
     * @param jsonPutBucketLogging The targetBucket of Bucket Logging.
     */
    public void setJsonPutBucketLogging(String jsonPutBucketLogging) {
        this.jsonPutBucketLogging = jsonPutBucketLogging;
    }
}
