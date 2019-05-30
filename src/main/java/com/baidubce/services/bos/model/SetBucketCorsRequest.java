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
 * Request object containing all the options for setting a bucket's CORS(Cross-Origin Resource Sharing).
 */
public class SetBucketCorsRequest extends GenericBucketRequest {


    /**
     * The json format for Bucket CORS.
     */
    private String jsonBucketCors;

    /**
     * The corsConfigurationsList of Bucket CORS.
     */
    private List<CorsConfiguration> corsConfigurationsList;


    /**
     * Constructs a void Constructor for SetBucketCorsRequest.
     */
    public SetBucketCorsRequest() {
    }

    /**
     * Constructs a new SetBucketCorsRequest object, ready to set the specified Bucket CORS.
     * @param bucketName
     * @param jsonBucketCors The jsonBucketCors of Bucket CORS.
     */
    public SetBucketCorsRequest(String bucketName, String jsonBucketCors) {
        super(bucketName);
        this.jsonBucketCors = jsonBucketCors;
    }

    /**
     * Constructs a new SetBucketCorsRequest object, ready to set the specified Bucket CORS.
     * @param bucketName
     * @param corsConfigurationsList The corsConfigurationsList of Bucket CORS.
     */
    public SetBucketCorsRequest(String bucketName, List<CorsConfiguration> corsConfigurationsList) {
        super(bucketName);
        this.corsConfigurationsList = corsConfigurationsList;
    }

    @Override
    public SetBucketCorsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public SetBucketCorsRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Gets the jsonBucketCors for Bucket CORS.
     * @return  the jsonBucketCors for Bucket CORS.
     */
    public String getJsonBucketCors() {
        return jsonBucketCors;
    }

    /**
     * Sets the jsonBucketCors for Bucket CORS.
     * @param jsonBucketCors The jsonBucketCors for Bucket CORS.
     */
    public void setJsonBucketCors(String jsonBucketCors) {
        this.jsonBucketCors = jsonBucketCors;
    }

    /**
     * Gets the corsConfigurationsList for Bucket CORS.
     * @return the corsConfigurationsList of Bucket CORS.
     */
    public List<CorsConfiguration> getCorsConfigurationsList() {
        return corsConfigurationsList;
    }

    /**
     * Sets the corsConfigurationsList for Bucket CORS.
     * @param corsConfigurationsList The corsConfigurationsList of Bucket CORS.
     */
    public void setCorsConfigurationsList(List<CorsConfiguration> corsConfigurationsList) {
        this.corsConfigurationsList = corsConfigurationsList;
    }
}
