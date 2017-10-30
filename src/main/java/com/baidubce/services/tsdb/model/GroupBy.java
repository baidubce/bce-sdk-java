package com.baidubce.services.tsdb.model;

import java.util.List;

/**
 * Represent the GroupBy for querying datapoints from Tsdb.
 */
public class GroupBy {
    
    /**
     * Required.
     * Represent the name of GroupBy which should be Tag.
     */
    private String name;
    
    /**
     * Required when name is Tag.
     * Represent the tags for grouping by tag.
     */
    private List<String> tags;
    
    /**
     * Required when name is Time.
     * Represent range size of time for grouping by time.
     */
    @Deprecated
    private String timeRangeSize;
    
    /**
     * Required when name is Time.
     * Represent the count of group for grouping by time.
     */
    @Deprecated
    private Integer groupCount;
    
    /**
     * Required when name is Value.
     * Represent the range size of value for grouping by value.
     */
    @Deprecated
    private Integer valueRangeSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Deprecated
    public String getTimeRangeSize() {
        return timeRangeSize;
    }

    @Deprecated
    public void setTimeRangeSize(String timeRangeSize) {
        this.timeRangeSize = timeRangeSize;
    }

    @Deprecated
    public Integer getGroupCount() {
        return groupCount;
    }

    @Deprecated
    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    @Deprecated
    public Integer getValueRangeSize() {
        return valueRangeSize;
    }

    @Deprecated
    public void setValueRangeSize(Integer valueRangeSize) {
        this.valueRangeSize = valueRangeSize;
    }
    
    public GroupBy withName(String name) {
        this.name = name;
        return this;
    }
    
    public GroupBy withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Deprecated
    public GroupBy withTimeRangeSize(String timeRangeSize) {
        this.timeRangeSize = timeRangeSize;
        return this;
    }

    @Deprecated
    public GroupBy withGroupCount(int groupCount) {
        this.groupCount = groupCount;
        return this;
    }

    @Deprecated
    public GroupBy withValueRangeSize(int valueRangeSize) {
        this.valueRangeSize = valueRangeSize;
        return this;
    }
}
