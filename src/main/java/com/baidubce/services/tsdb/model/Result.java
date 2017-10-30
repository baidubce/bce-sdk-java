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
