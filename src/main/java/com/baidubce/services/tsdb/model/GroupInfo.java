package com.baidubce.services.tsdb.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represent the GroupBy for querying datapoints from Tsdb.
 */
public class GroupInfo {

    /**
     * The name of GroupInfo which should be Tag.
     */
    private String name;

    /**
     * Available when the name is Type.
     * Represent the type of this group which is one of the Number, String and Bytes.
     */
    @Deprecated
    private String type;

    /**
     * Available when name is Tag.
     * Represent the tags this group have.
     */
    private Map<String, String> tags;

    /**
     * Available when name is Time.
     * Represent the group number of this group.
     */
    @Deprecated
    private Integer groupNumber;

    /**
     * Available when name is Value.
     * Represent the beginning of this group. The type could be Long/Double.
     */
    @Deprecated
    private JsonNode from;

    /**
     * Available when name is Value.
     * Represent the ending of this group. The type could be Long/Double.
     */
    @Deprecated
    private JsonNode to;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Deprecated
    public String getType() {
        return type;
    }

    @Deprecated
    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    @Deprecated
    public Integer getGroupNumber() {
        return groupNumber;
    }

    @Deprecated
    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Deprecated
    public JsonNode getFrom() {
        return from;
    }

    @JsonIgnore
    @Deprecated
    public long getLongFrom() {
        return from.asLong();
    }

    @JsonIgnore
    @Deprecated
    public double getDoubleFrom() {
        return from.asDouble();
    }

    @JsonIgnore
    @Deprecated
    public boolean isLongFrom() {
        return from.isLong();
    }

    @JsonIgnore
    @Deprecated
    public boolean isDoubleFrom() {
        return from.isDouble();
    }

    @Deprecated
    public void setFrom(JsonNode from) {
        this.from = from;
    }

    @Deprecated
    public JsonNode getTo() {
        return to;
    }

    @JsonIgnore
    @Deprecated
    public long getLongTo() {
        return to.asLong();
    }

    @JsonIgnore
    @Deprecated
    public double getDoubleTo() {
        return to.asDouble();
    }

    @JsonIgnore
    @Deprecated
    public boolean isLongTo() {
        return to.isLong();
    }

    @JsonIgnore
    @Deprecated
    public boolean isDoubleTo() {
        return to.isDouble();
    }

    @Deprecated
    public void setTo(JsonNode to) {
        this.to = to;
    }
}
