/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bci.model.common;

/**
 * The tag of resource
 */
public class Tag {

    /**
     * The key of tag
     */
    private String tagKey;

    /**
     * The value of tag
     */
    private String tagValue;

    /**
     * Construct a new Tag object.
     */
    public Tag() {
    }

    /**
     * Construct a new Tag object.
     *
     * @param tagKey   The key of tag
     * @param tagValue The value of tag
     */
    public Tag(String tagKey, String tagValue) {
        this.tagKey = tagKey;
        this.tagValue = tagValue;
    }

    public String getTagKey() {
        return tagKey;
    }

    public Tag setTagKey(String tagKey) {
        this.tagKey = tagKey;
        return this;
    }

    public String getTagValue() {
        return tagValue;
    }

    public Tag setTagValue(String tagValue) {
        this.tagValue = tagValue;
        return this;
    }

    @Override
    public String toString() {
        return "Tag{"
                + "tagKey='" + tagKey + '\''
                + ", tagValue='" + tagValue + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tag tag = (Tag) o;

        if (tagKey != null ? !tagKey.equals(tag.tagKey) : tag.tagKey != null) {
            return false;
        }
        return tagValue != null ? tagValue.equals(tag.tagValue) : tag.tagValue == null;
    }

    @Override
    public int hashCode() {
        int result = tagKey != null ? tagKey.hashCode() : 0;
        result = 31 * result + (tagValue != null ? tagValue.hashCode() : 0);
        return result;
    }
}