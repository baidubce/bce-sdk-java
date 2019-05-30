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
 *  Request for get Bucket CORS(Cross-Origin Resource Sharing).
 */
public class GetBucketCorsRequest extends GenericBucketRequest {

    /**
     * Constructs a void Constructor for GetBucketCorsRequest.
     */
    public GetBucketCorsRequest() {
    }

    /**
     * Constructs a new GetBucketCorsRequest to get a specified Bucket CORS.
     * @param bucketName
     */
    public GetBucketCorsRequest(String bucketName) {
        super(bucketName);
    }

    @Override
    public GetBucketCorsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public GetBucketCorsRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

}
