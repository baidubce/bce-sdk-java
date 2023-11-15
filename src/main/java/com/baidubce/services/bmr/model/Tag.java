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
package com.baidubce.services.bmr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {
    private String tagKey;
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

    public Tag withTagKey(String tagKey) {
        this.tagKey = tagKey;
        return this;
    }

    public Tag withTagValue(String tagValue) {
        this.tagValue = tagValue;
        return this;
    }

    @Override
    public String toString() {
        return "Tag{"
                + "tagKey='" + tagKey + '\''
                + ", tagValue='" + tagValue + '\''
                + " }";
    }
}
