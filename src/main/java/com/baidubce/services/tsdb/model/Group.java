package com.baidubce.services.tsdb.model;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.baidubce.services.tsdb.TsdbConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BinaryNode;
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

        boolean isBytes = false;
        for (GroupInfo groupInfo : groupInfos) {
            if (groupInfo.getName().equals(TsdbConstants.GROUP_INFO_NAME_TYPE)
                    && groupInfo.getType().equals(TsdbConstants.TYPE_BYTES)) {
                isBytes = true;
                break;
            }
        }

        List<TimeAndValue> list = Lists.newArrayList();
        for (List<JsonNode> nodeList : values) {
            if (isBytes && nodeList.get(1).isTextual()) {
                list.add(new TimeAndValue(nodeList.get(0).asLong(),
                        new BinaryNode(nodeList.get(1).binaryValue())));
            } else {
                list.add(new TimeAndValue(nodeList.get(0).asLong(), nodeList.get(1)));
            }
        }
        return list;
    }

    public static class TimeAndValue {

        private long time;

        private JsonNode value;

        public TimeAndValue(long time, JsonNode value) {
            this.time = time;
            this.value = value;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public JsonNode getValue() {
            return value;
        }

        public void setValue(JsonNode value) {
            this.value = value;
        }

        @JsonIgnore
        public boolean isLong() {
            return value.isIntegralNumber();
        }

        @JsonIgnore
        public boolean isDouble() {
            return value.isFloatingPointNumber();
        }

        @JsonIgnore
        public boolean isString() {
            return value.isTextual();
        }

        @JsonIgnore
        public boolean isBytes() {
            return value.isBinary();
        }

        @JsonIgnore
        public long getLongValue() {
            return value.asLong();
        }

        @JsonIgnore
        public double getDoubleValue() {
            return value.asDouble();
        }

        @JsonIgnore
        public String getStringValue() {
            return value.asText();
        }

        @JsonIgnore
        public byte[] getBytesValue() throws IOException {
            return value.binaryValue();
        }
    }
}