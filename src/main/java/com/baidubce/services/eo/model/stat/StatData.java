package com.baidubce.services.eo.model.stat;

import com.baidubce.services.eo.model.JsonObject;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A single data point of a stat metric.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class StatData extends JsonObject {
    /**
     * The timestamp. It is null for peak/sum/top show types.
     */
    private Integer timestamp;

    /**
     * The group params.
     */
    private Object groupParams;

    /**
     * The metric value. It is an integer value.
     */
    private Long value;

    /**
     * @return timestamp
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return groupParams
     */
    public Object getGroupParams() {
        return groupParams;
    }

    /**
     * @param groupParams
     */
    public void setGroupParams(Object groupParams) {
        this.groupParams = groupParams;
    }

    /**
     * @return value
     */
    public Long getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(Long value) {
        this.value = value;
    }
}
