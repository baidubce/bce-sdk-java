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
 * Request object containing all the options for setting a bucket's staticwebsite.
 */
public class SetBucketStaticWebsiteRequest extends  GenericBucketRequest {

    /**
     * The index page for staticwebsite
     */
    private String index;

    /**
    * The notFound page for staticwebsite
    */
    private String notFound;

    /**
     * The json format bucket staticwebsite for bucket
     */
    private String jsonBucketStaticWebsite;

    public SetBucketStaticWebsiteRequest() {

    }

    /**
     * Constructs a new SetBucketStaticWebsiteRequest object, ready to set the specified Bucket StaticWebsite config.
     * @param bucketName
     * @param index The index page for staticwebsite.
     * @param notFound The notFound page for staticwebsite.
     */
    public SetBucketStaticWebsiteRequest(String bucketName, String index, String notFound) {
        super(bucketName);
        this.index = index;
        this.notFound = notFound;
    }

    public SetBucketStaticWebsiteRequest(String bucketName, String jsonBucketStaticWebsite) {
        this.setBucketName(bucketName);
        this.jsonBucketStaticWebsite = jsonBucketStaticWebsite;
    }

    @Override
    public SetBucketStaticWebsiteRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public SetBucketStaticWebsiteRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getNotFound() {
        return notFound;
    }

    public void setNotFound(String notFound) {
        this.notFound = notFound;
    }

    public String getJsonBucketStaticWebsite() {
        return jsonBucketStaticWebsite;
    }

    public void setJsonBucketStaticWebsite(String jsonBucketStaticWebsite) {
        this.jsonBucketStaticWebsite = jsonBucketStaticWebsite;
    }
}
