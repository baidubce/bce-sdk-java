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

import java.util.List;

/**
 * For Bucket Acl Condition.
 * Identifies the referer that is granted access permission.
 */
public class Referer {

    /**
     * Identify the fuzzy matching address in the referer's whitelist.
     */
    private List<String> stringLike;

    /**
     * Identify the exact matching address in the referer's whitelist.
     */
    private List<String> stringEquals;

    /**
     * Gets the stringLike of Bucket Acl Condition Referer.
     * @return the stringLike of Bucket Acl Condition Referer.
     */
    public List<String> getStringLike() {
        return stringLike;
    }

    /**
     * Sets the stringLike of Bucket Acl Condition Referer.
     * @param stringLike The stringLike of Bucket Acl Condition Referer.
     */
    public void setStringLike(List<String> stringLike) {
        this.stringLike = stringLike;
    }

    /**
     * Sets the stringLike of Bucket Acl Condition Referer.
     * @param stringLike The stringLike of Bucket Acl Condition Referer.
     * @return this object.
     */
    public Referer withStringLike (List<String> stringLike) {
        this.setStringLike(stringLike);
        return this;
    }

    /**
     * Gets the stringEquals of Bucket Acl Condition Referer.
     * @return the stringEquals of Bucket Acl Condition Referer.
     */
    public List<String> getStringEquals() {
        return stringEquals;
    }

    /**
     * Sets the stringEquals of Bucket Acl Condition Referer.
     * @param stringEquals The stringEquals of Bucket Acl Condition Referer.
     */
    public void setStringEquals(List<String> stringEquals) {
        this.stringEquals = stringEquals;
    }

    /**
     * Sets the stringEquals of Bucket Acl Condition Referer.
     * @param stringEquals The stringEquals of Bucket Acl Condition Referer.
     * @return this object.
     */
    public Referer withStringEquals (List<String> stringEquals) {
        this.setStringEquals(stringEquals);
        return this;
    }

    @Override
    public String toString() {
        return "Referer{"
                + "stringLike=" + stringLike
                + ", stringEquals=" + stringEquals
                + '}';
    }
}
