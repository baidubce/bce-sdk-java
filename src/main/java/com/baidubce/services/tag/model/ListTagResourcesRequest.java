/*
 * Copyright (c) 2018 Baidu.com, Inc. All Rights Reserved
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.tag.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ListTagResourcesRequest extends AbstractBceRequest {
    // filtered by tag key
    private String tagKey;
    // filtered by tag value
    private String tagValue;
    // filtered by resource type
    private String resourceType;
    // filtered by region
    private String region;

    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tagKey) {
        this.tagKey = tagKey;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ListTagResourcesRequest withTagKey(String tagKey) {
        this.tagKey = tagKey;
        return this;
    }

    public ListTagResourcesRequest withTagValue(String tagValue) {
        this.tagValue = tagValue;
        return this;
    }

    public ListTagResourcesRequest withResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public ListTagResourcesRequest withRegion(String region) {
        this.region = region;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListTagResourcesRequest with credentials.
     */
    @Override
    public ListTagResourcesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return "ListTagResourcesRequest{"
                + "tagKey='" + tagKey + '\''
                + ", tagValue='" + tagValue + '\''
                + ", resourceType='" + resourceType + '\''
                + ", region='" + region + '\''
                + " }";
    }
}
