package com.baidubce.services.tsdb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Represent the filters for querying datapoints from Tsdb.
 */
public class Filters {

    /**
     * Required.
     * The start time. It presents absolute time when type is Long, the unit is millisecond; Or it presents
     * relative time when type is String, like "5 days ago".
     */
    private JsonNode start;

    /**
     * Optional.
     * The end time. like start above.
     */
    private JsonNode end;

    /**
     * Optional.
     * The tag key-values map. Means the datapoints with tags that the value of the corresponding key
     * should match.
     */
    private Map<String, List<String>> tags;

    /**
     * Optional.
     * The value filter for single field or all multiple fields.
     * It contains two parts. The first part is a operation such as <, <=, =, !=, > and >=.
     * The second part is a value which can be a long type number, a double type number or a string surrounding by
     * single quotations.
     * Long or double value support <, <=, =, !=, > and >= operations.
     * String value only support =, !=.
     * Example: "= 111", "> 1.1", "!= 'abc'".
     * Conflict with fields parameter.
     *
     */
    private String value;

    /**
     * Optional.
     * The value filter for each multiple fields.
     * Conflict with value parameter.
     */
    private List<FieldFilter> fields;

    public JsonNode getStart() {
        return start;
    }

    public void setStart(JsonNode start) {
        this.start = start;
    }

    public JsonNode getEnd() {
        return end;
    }

    public void setEnd(JsonNode end) {
        this.end = end;
    }

    public Map<String, List<String>> getTags() {
        return tags;
    }

    public void setTags(Map<String, List<String>> tags) {
        this.tags = tags;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<FieldFilter> getFields() {
        return fields;
    }

    public void setFields(List<FieldFilter> fields) {
        this.fields = fields;
    }

    /**
     * Set value for filter.
     *
     * @param value The value for filter.
     * @return Filters
     */
    public Filters withValue(ValueFilter value) {
        this.value = value.getOperation() + " " + value.getValue();
        return this;
    }

    /**
     * Set value for filter.
     *
     * @param value
     * @return
     */
    public Filters withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Set field filters.
     *
     * @param fields  The field filters list.
     * @return Filters
     */
    public Filters withFields(List<FieldFilter> fields) {
        this.fields = fields;
        return this;
    }

    /**
     * Set absolute start time.
     *
     * @param start The unit is millisecond.
     * @return Filters
     */
    public Filters withAbsoluteStart(long start) {
        this.start = new LongNode(start);
        return this;
    }

    /**
     * Set relative start time, like "5 days ago", "2 minutes ago" or "3 seconds ago".
     *
     * @param start
     * @return Filters
     */
    public Filters withRelativeStart(String start) {
        this.start = new TextNode(start);
        return this;
    }

    /**
     * Set absolute end time.
     *
     * @param end The unit is millisecond.
     * @return Filters
     */
    public Filters withAbsoluteEnd(long end) {
        this.end = new LongNode(end);
        return this;
    }

    /**
     * Set relative end time, like "5 days ago", "2 minutes ago" or "3 seconds ago".
     *
     * @param end
     * @return Filters
     */
    public Filters withRelativeEnd(String end) {
        this.end = new TextNode(end);
        return this;
    }

    public Filters withTags(Map<String, List<String>> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Add field filter to fields which just append not replace.
     *
     * @param field The field filter
     * @return Filters
     */
    public Filters addField(FieldFilter field) {
        initialFields();
        fields.add(field);
        return this;
    }

    /**
     * Add field filter to fields which just append not replace.
     *
     * @param field The field name for filter
     * @param value The value filter
     * @return Filters
     */
    public Filters addField(String field, String value) {
        initialFields();
        fields.add(new FieldFilter(field, value));
        return this;
    }

    /**
     * Add tag to tags which just append not replace.
     *
     * @param tagKey
     * @param tagValues
     * @return Filters
     */
    public Filters addTag(String tagKey, List<String> tagValues) {
        addTags(tagKey, tagValues);
        return this;
    }

    /**
     * Add tag to tags which just append not replace.
     *
     * @param tagKey
     * @param tagValues
     * @return Filters
     */
    public Filters addTag(String tagKey, String...tagValues) {
        addTags(tagKey, tagValues);
        return this;
    }

    private void addTags(String tagKey, List<String> tagValues) {
        initialTags();
        if (tags.containsKey(tagKey)) {
            tags.get(tagKey).addAll(tagValues);
        } else {
            tags.put(tagKey, Lists.newArrayList(tagValues));
        }
    }

    private void addTags(String tagKey, String...tagValues) {
        initialTags();
        if (tags.containsKey(tagKey)) {
            for (String tagValue : tagValues) {
                tags.get(tagKey).add(tagValue);
            }
        } else {
            tags.put(tagKey, Lists.newArrayList(tagValues));
        }
    }

    private void initialTags() {
        if (tags == null) {
            tags = Maps.newHashMap();
        }
    }

    private void initialFields() {
        if (fields == null) {
            fields = new ArrayList<FieldFilter>();
        }
    }
}
