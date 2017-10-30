package com.baidubce.services.tsdb.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;

/**
 * Represent the fill in query.
 *
 * @author linpengxiang
 */
public class Fill {

    /**
     * Required.
     * The type of interpolation which can be "Linear" or "Previous".
     */
    private String type;

    /**
     * Required.
     * The interval for interpolation. eg: "1 minute" or "5 hours".
     * It will Interpolate a value when there are not value in the interval.
     */
    private String interval;

    /**
     * Optional.
     * The maximum write interval for data points. eg: "1 minute", or "5 hours".
     * It will consider data points in [start - maxWriteInterval, end + maxWriteInterval] for interpolation.
     */
    private String maxWriteInterval;

    /**
     * Required when type is {@link com.baidubce.services.tsdb.TsdbConstants#FILL_TYPE_FIXED}.
     */
    private JsonNode value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getMaxWriteInterval() {
        return maxWriteInterval;
    }

    public void setMaxWriteInterval(String maxWriteInterval) {
        this.maxWriteInterval = maxWriteInterval;
    }

    public JsonNode getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = new LongNode(value);
    }

    public void setValue(double value) {
        this.value = new DoubleNode(value);
    }

    public void setValue(String value) {
        this.value = new TextNode(value);
    }

    public Fill withType(String type) {
        setType(type);
        return this;
    }

    public Fill withInterval(String interval) {
        setInterval(interval);
        return this;
    }

    public Fill withMaxWriteInterval(String maxWriteInterval) {
        setMaxWriteInterval(maxWriteInterval);
        return this;
    }

    public Fill withValue(long value) {
        setValue(value);
        return this;
    }

    public Fill withValue(double value) {
        setValue(value);
        return this;
    }

    public Fill withValue(String value) {
        setValue(value);
        return this;
    }

}
