package com.baidubce.services.tsdb.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;

/**
 * Represent the Group for querying datapoints from Tsdb.
 */
public class Group {

    /**
     * GroupInfo list.
     */
    private List<GroupInfo> groupInfos = Lists.newArrayList();

    /**
     * The sequence of time and value. The inner list has, only has two elements, the first is time with Long type;
     * and the second is value, the type could be Long/Double/String
     */
    private List<List<JsonNode>> values = Lists.newArrayList();

    public List<GroupInfo> getGroupInfos() {
        return groupInfos;
    }

    public void setGroupInfos(List<GroupInfo> groupInfos) {
        this.groupInfos = groupInfos;
    }

    public List<List<JsonNode>> getValues() {
        return values;
    }

    public void setValues(List<List<JsonNode>> values) {
        this.values = values;
    }

    @JsonIgnore
    public List<TimeAndValue> getTimeAndValueList() throws IOException {
        if (values == null || values.isEmpty()) {
            return Collections.emptyList();
        }

        List<TimeAndValue> list = Lists.newArrayList();
        for (List<JsonNode> nodeList : values) {
            long timestamp = nodeList.get(0).asLong();
            List<JsonNode> values = new ArrayList<JsonNode>();
            for (int index = 1; index < nodeList.size(); index++) {
                values.add(nodeList.get(index));
            }
            list.add(new TimeAndValue(timestamp, values));
        }
        return list;
    }

    public static class TimeAndValue {

        private long time;

        private List<? extends JsonNode> value;

        public TimeAndValue(long time, JsonNode value) {
            this.time = time;
            this.value = Collections.singletonList(value);
        }

        public TimeAndValue(long time, List<? extends JsonNode> values) {
            this.time = time;
            this.value = values;
        }

        public int getValueLength() {
            return value.size();
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public JsonNode getValue() {
            return value.get(0);
        }

        public void setValue(JsonNode value) {
            this.value = Collections.singletonList(value);
        }

        @JsonIgnore
        public boolean isLong() {
            return value.get(0).isIntegralNumber();
        }

        @JsonIgnore
        public boolean isLong(int index) {
            return value.get(index).isIntegralNumber();
        }

        @JsonIgnore
        public boolean isDouble() {
            return value.get(0).isFloatingPointNumber();
        }

        @JsonIgnore
        public boolean isDouble(int index) {
            return value.get(index).isFloatingPointNumber();
        }

        @Deprecated
        @JsonIgnore
        public boolean isString() {
            throw new RuntimeException("Interface is deprecated");
        }

        @Deprecated
        @JsonIgnore
        public boolean isBytes() {
            throw new RuntimeException("Interface is deprecated");
        }

        @JsonIgnore
        public long getLongValue() {
            return value.get(0).asLong();
        }

        @JsonIgnore
        public long getLongValue(int index) {
            return value.get(index).asLong();
        }

        @JsonIgnore
        public double getDoubleValue() {
            return value.get(0).asDouble();
        }

        @JsonIgnore
        public double getDoubleValue(int index) {
            return value.get(index).asDouble();
        }

        @JsonIgnore
        public String getStringValue() {
            return value.get(0).asText();
        }

        @JsonIgnore
        public String getStringValue(int index) {
            return value.get(index).asText();
        }

        @JsonIgnore
        public byte[] getBytesValue() throws IOException {
            return value.get(0).binaryValue();
        }

        @JsonIgnore
        public byte[] getBytesValue(int index) throws IOException {
            return value.get(index).binaryValue();
        }

        @JsonIgnore
        public boolean isNull() {
            return value.get(0).isNull();
        }

        @JsonIgnore
        public boolean isNull(int index) {
            return value.get(index).isNull();
        }
    }
}