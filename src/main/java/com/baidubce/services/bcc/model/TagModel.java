/*
 * Copyright (c) 2018 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model;

/**
 * The detail model of the tag.
 */
public class TagModel {

    /**
     * The key of tag.
     */
    private String tagKey ;

    /**
     * The value of tag.
     */
    private String tagValue;

    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tagKey) {
        this.tagKey = tagKey;
    }

    /**
     * Configure tagKey for the model.
     *
     * @param tagKey The key of tag.
     * @return TagModel with tagKey.
     */
    public TagModel withTagKey(String tagKey) {
        this.tagKey = tagKey;
        return this;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    /**
     * Configure tagValue for the model.
     *
     * @param tagValue The value of tag.
     * @return TagModel with tagValue.
     */
    public TagModel withTagValue(String tagValue) {
        this.tagValue = tagValue;
        return this;
    }

    @Override
    public String toString() {
        return "TagModel{"
                + ", tagKey='" + tagKey + '\''
                + ", tagValue='" + tagValue + '\''
                + '}';
    }
}
