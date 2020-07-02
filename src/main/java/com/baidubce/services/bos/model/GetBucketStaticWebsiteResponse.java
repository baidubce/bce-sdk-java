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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Get Bucket StaticWebsite Response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBucketStaticWebsiteResponse extends BosResponse {

    /**
     * The index page for staticwebsite
     */
    private String index;

    /**
     * The notFound page for staticwebsite
     */
    private String notFound;

    public GetBucketStaticWebsiteResponse() {

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

    @Override
    public String toString() {
        return "GetBucketStaticWebsiteResponse{"
                + "index='" + index + '\''
                + ", notFound='" + notFound + '\''
                + '}';
    }
}
