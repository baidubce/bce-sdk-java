package com.baidubce.services.tsdb.model;

import static com.baidubce.services.tsdb.TsdbConstants.ValueType.TYPE_BIG_DECIMAL;
import static com.baidubce.services.tsdb.TsdbConstants.ValueType.TYPE_BYTES;
import static com.baidubce.services.tsdb.TsdbConstants.ValueType.TYPE_DOUBLE;
import static com.baidubce.services.tsdb.TsdbConstants.ValueType.TYPE_LONG;
import static com.baidubce.services.tsdb.TsdbConstants.ValueType.TYPE_STRING;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BinaryNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Represent the model of datapoint for Tsdb.
 */
public class Datapoint {

    /**
     * Required.
     * Represent the metric of the datapoint(s).
     */
    private String metric;

    /**
     * Optional.
     * Represent the field of the datapoint(s).
     */
    private String field;

    /**
     * Required.
     * Represent the tags which the datapoint(s) has/have.
     */
    private Map<String, String> tags;

    /**
     * Required.
     * Represent the second element's type in {@see #values}'s inner list.
     * And Bytes type is special, it represents the string value is a base64 encoded byte array.
     */
    private String type;

    /**
     * The sequence of time and value. The inner list has, only has two elements, the first is time with Long type;
     * and the second is value, the type could be Long/Double/String.
     */
    private List<List<JsonNode>> values;

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

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public List<List<JsonNode>> getValues() {
        return values;
    }

    public void setValues(List<List<JsonNode>> values) {
        this.values = values;
    }

    public Datapoint withMetric(String metric) {
        this.metric = metric;
        return this;
    }

    public Datapoint withField(String field) {
        this.field = field;
        return this;
    }

    public Datapoint withTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Add datapoint of long type value.
     *
     * @param time  datapoint's timestamp
     * @param value datapoint's value
     * @return Datapoint
     */
    public Datapoint addLongValue(long time, long value) {
        initialValues(TYPE_LONG);

        values.add(Lists.<JsonNode>newArrayList(new LongNode(time), new LongNode(value)));
        return this;
    }

    /**
     * Add datapoint of double type value.
     *
     * @param time  datapoint's timestamp
     * @param value datapoint's value
     * @return Datapoint
     */
    public Datapoint addDoubleValue(long time, double value) {
        initialValues(TYPE_DOUBLE);

        values.add(Lists.<JsonNode>newArrayList(new LongNode(time), new DoubleNode(value)));
        return this;
    }

    /**
     * Add datapoint of String type value.
     *
     * @param time  datapoint's timestamp
     * @param value datapoint's value
     * @return Datapoint
     */
    public Datapoint addStringValue(long time, String value) {
        initialValues(TYPE_STRING);

        values.add(Lists.<JsonNode>newArrayList(new LongNode(time), new TextNode(value)));
        return this;
    }

    public Datapoint addBytesValue(long time, byte[] value) {
        initialValues(TYPE_BYTES);

        values.add(Lists.<JsonNode>newArrayList(new LongNode(time), new BinaryNode(value)));
        return this;
    }

    public Datapoint addBigDecimalValue(long time, BigDecimal value) {
        initialValues(TYPE_BIG_DECIMAL);

        values.add(Lists.<JsonNode>newArrayList(new LongNode(time), new TextNode(String.valueOf(value))));
        return this;
    }

    /**
     * Add tag for the datapoint.
     *
     * @param tagKey
     * @param tagValue
     * @return Datapoint
     */
    public Datapoint addTag(String tagKey, String tagValue) {
        initialTags();
        tags.put(tagKey, tagValue);
        return this;
    }

    private void initialValues(String inputType) {
        if (values == null) {
            values = Lists.newArrayList();
            type = inputType;
            return;
        }
        if (!type.equals(inputType)) {
            throw new IllegalStateException("There is already " + type
                    + " type in datapoint, could not add " + inputType + " type again");
        }
    }

    private void initialTags() {
        if (tags == null) {
            tags = Maps.newHashMap();
        }
    }

}