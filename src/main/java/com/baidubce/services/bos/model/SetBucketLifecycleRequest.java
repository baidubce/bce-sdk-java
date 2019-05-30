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

import java.util.List;

/**
 * Request object containing all the options for setting a bucket's Lifecycle.
 */
public class SetBucketLifecycleRequest extends GenericBucketRequest {

    /**
     * The json format for Bucket Lifecycle.
     */
    private String jsonBucketLifecycle;

    /**
     * The ruleList of Bucket Lifecycle.
     */
    private List<Rule> ruleList;

    /**
     * Constructs a void Constructor for SetBucketLifecycleRequest.
     */
    public SetBucketLifecycleRequest() {

    }

    /**
     * Constructs a new SetBucketLifecycleRequest object, ready to set the specified Bucket Lifecycle.
     * @param bucketName
     * @param jsonBucketLifecycle The jsonBucketLifecycle of Bucket Lifecycle.
     */
    public SetBucketLifecycleRequest(String bucketName, String jsonBucketLifecycle) {
        super(bucketName);
        this.jsonBucketLifecycle = jsonBucketLifecycle;
    }

    /**
     * Constructs a new SetBucketLifecycleRequest object, ready to set the specified Bucket Lifecycle.
     * @param bucketName
     * @param ruleList The ruleList of Bucket Lifecycle.
     */
    public SetBucketLifecycleRequest(String bucketName, List<Rule> ruleList) {
        super(bucketName);
        this.ruleList = ruleList;
    }

    @Override
    public SetBucketLifecycleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public SetBucketLifecycleRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Gets the jsonBucketLifecycle of Bucket Lifecycle.
     * @return the jsonBucketLifecycle of Bucket Lifecycle.
     */
    public String getJsonBucketLifecycle() {
        return jsonBucketLifecycle;
    }

    /**
     * Sets the jsonBucketLifecycle of Bucket Lifecycle.
     * @param jsonBucketLifecycle The jsonBucketLifecycle of Bucket Lifecycle.
     */
    public void setJsonBucketLifecycle(String jsonBucketLifecycle) {
        this.jsonBucketLifecycle = jsonBucketLifecycle;
    }

    /**
     * Gets the ruleList of Bucket Lifecycle.
     * @return the ruleList of Bucket Lifecycle.
     */
    public List<Rule> getRuleList() {
        return ruleList;
    }

    /**
     * Sets the ruleList of Bucket Lifecycle.
     * @param ruleList The ruleList of Bucket Lifecycle.
     */
    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }
}
