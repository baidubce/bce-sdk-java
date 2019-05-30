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
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ListTagsRequest extends AbstractBceRequest {
    // Filtered by tag key
    @JsonIgnore
    private String tagKey;
    // Filtered by tag value
    @JsonIgnore
    private String tagValue;

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

    public ListTagsRequest withTagKey(String tagKey) {
        this.tagKey = tagKey;
        return this;
    }

    public ListTagsRequest withTagValue(String tagValue) {
        this.tagValue = tagValue;
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListTagsRequest with credentials.
     */
    @Override
    public ListTagsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return "ListTagsRequest{"
                + "tagKey='" + tagKey + '\''
                + ", tagValue='" + tagValue + '\''
                + " }";
    }
}
