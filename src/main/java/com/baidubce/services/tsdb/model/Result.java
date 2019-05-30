/*
 * Copyright 2018-2019 Baidu, Inc.
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

import java.util.List;

/**
 * Represent the result for querying datapoints from Tsdb.
 */
public class Result {
    
    private String metric;

    private String field;

    private List<String> fields;

    private List<String> tags;

    private long rawCount;

    private long consumedCount;
    
    private List<Group> groups;

    private Boolean truncated;

    private String nextMarker;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public long getRawCount() {
        return rawCount;
    }

    public void setRawCount(long rawCount) {
        this.rawCount = rawCount;
    }

    public long getConsumedCount() {
        return consumedCount;
    }

    public void setConsumedCount(long consumedCount) {
        this.consumedCount = consumedCount;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public boolean isTruncated() {
        return truncated != null && truncated;
    }

    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }
}
