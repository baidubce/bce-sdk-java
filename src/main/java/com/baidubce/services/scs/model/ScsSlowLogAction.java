package com.baidubce.services.scs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The action to modify scs slow log
 */
public enum ScsSlowLogAction {
    OPEN("open"),
    CLOSE("close");

    private final String value;

    ScsSlowLogAction(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static ScsSlowLogAction create(String value) {
        if (OPEN.value.equalsIgnoreCase(value)) {
            return OPEN;
        } else {
            return CLOSE;
        }
    }
}
