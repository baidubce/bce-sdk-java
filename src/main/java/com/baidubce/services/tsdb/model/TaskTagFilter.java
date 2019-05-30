/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.tsdb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Task tag filter.
 *
 */
public class TaskTagFilter {

    /**
     * The tag key.
     */
    private String tagKey;

    /**
     * The tag values in which the tag value of this tag key should be.
     */
    private List<String> in = new ArrayList<String>();

    /**
     * The tag values in which the tag value of this tag key should not be.
     */
    private List<String> notIn = new ArrayList<String>();

    /**
     * The like pattern that tag value should match.
     */
    private String like;

    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tag) {
        this.tagKey = tag;
    }

    public List<String> getIn() {
        return in;
    }

    public void setIn(List<String> in) {
        this.in = in;
    }

    public List<String> getNotIn() {
        return notIn;
    }

    public void setNotIn(List<String> notIn) {
        this.notIn = notIn;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public TaskTagFilter withTag(String tag) {
        this.tagKey = tag;
        return this;
    }

    public TaskTagFilter withIn(List<String> in) {
        this.notIn = null;
        this.in = in;
        return this;
    }

    public TaskTagFilter withNotIn(List<String> notIn) {
        this.in = null;
        this.notIn = notIn;
        return this;
    }

    public TaskTagFilter addIn(String tagValue) {
        if (in == null) {
            this.notIn = null;
            in = new ArrayList<String>();
        }
        in.add(tagValue);
        return this;
    }

    public TaskTagFilter addNotIn(String tagValue) {
        if (notIn == null) {
            this.in = null;
            notIn = new ArrayList<String>();
        }
        notIn.add(tagValue);
        return this;
    }

    public TaskTagFilter like(String like) {
        this.like = like;
        return this;
    }

}
