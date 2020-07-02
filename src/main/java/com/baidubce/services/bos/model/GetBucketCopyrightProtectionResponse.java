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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Get Bucket CopyrightProtection Response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBucketCopyrightProtectionResponse extends BosResponse {

    /**
     * corsConfiguration of GetBucketCorsResponse.
     */
    private List<String> resource;

    /**
     * Constructs a void constructor for GetBucketCopyrightProtectionResponse.
     */
    public GetBucketCopyrightProtectionResponse() {
    }

    /**
     * Constructs a new GetBucketCopyrightProtectionResponse object .
     * @param resource
     */
    public GetBucketCopyrightProtectionResponse(List<String> resource) {
        this.resource = resource;

    }

    /**
     * Gets CopyrightProtection of GetBucketCopyrightProtectionResponse(.
     * @return CopyrightProtection of GetBucketCopyrightProtectionResponse(.
     */
    public List<String> getResource() {
        return resource;
    }

    /**
     * Sets resource of GetBucketCorsResponse.
     * @param resource .
     */
    public void setResource(List<String> resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "GetBucketCopyrightProtectionResponse{"
                + "resource=" + resource
                + '}';
    }
}
