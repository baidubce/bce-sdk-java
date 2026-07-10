package com.baidubce.services.eo.model.stat;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * The filter condition of a stat metric query.
 */
public class FilterItem extends JsonObject {
    /**
     * The filter key.
     */
    private String key;

    /**
     * The values corresponding to the key.
     */
    private List<String> value;

    /**
     * The operation type.
     */
    private String operation;

    /**
     * @param key
     * @return this object
     */
    public FilterItem withKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @param value
     * @return this object
     */
    public FilterItem withValue(List<String> value) {
        this.value = value;
        return this;
    }

    /**
     * @return value
     */
    public List<String> getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(List<String> value) {
        this.value = value;
    }

    /**
     * @param operation
     * @return this object
     */
    public FilterItem withOperation(String operation) {
        this.operation = operation;
        return this;
    }

    /**
     * @return operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
}
