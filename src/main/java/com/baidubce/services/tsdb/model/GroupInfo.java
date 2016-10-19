package com.baidubce.services.tsdb.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represent the GroupBy for querying datapoints from Tsdb.
 */
public class GroupInfo {

    /**
     * The name of GroupInfo which should be one of the Type, Tag, Time and Value.
     */
    private String name;

    /**
     * Available when the name is Type.
     * Represent the type of this group which is one of the Number, String and Bytes.
     */
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
    private Integer groupNumber;

    /**
     * Available when name is Value.
     * Represent the beginning of this group. The type could be Long/Double.
     */
    private JsonNode from;

    /**
     * Available when name is Value.
     * Represent the ending of this group. The type could be Long/Double.
     */
    private JsonNode to;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public JsonNode getFrom() {
        return from;
    }

    @JsonIgnore
    public long getLongFrom() {
        return from.asLong();
    }

    @JsonIgnore
    public double getDoubleFrom() {
        return from.asDouble();
    }

    @JsonIgnore
    public boolean isLongFrom() {
        return from.isLong();
    }

    @JsonIgnore
    public boolean isDoubleFrom() {
        return from.isDouble();
    }

    public void setFrom(JsonNode from) {
        this.from = from;
    }

    public JsonNode getTo() {
        return to;
    }

    @JsonIgnore
    public long getLongTo() {
        return to.asLong();
    }

    @JsonIgnore
    public double getDoubleTo() {
        return to.asDouble();
    }

    @JsonIgnore
    public boolean isLongTo() {
        return to.isLong();
    }

    @JsonIgnore
    public boolean isDoubleTo() {
        return to.isDouble();
    }

    public void setTo(JsonNode to) {
        this.to = to;
    }
}
