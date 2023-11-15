package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The auto renew time unit
 */
public enum RdsRenewTimeUnit {
    YEAR("year"),
    MONTH("month");

    private final String value;

    RdsRenewTimeUnit(String value) {
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
    public static RdsRenewTimeUnit create(String value) {
        if (YEAR.value.equalsIgnoreCase(value)) {
            return YEAR;
        } else {
            return MONTH;
        }
    }
}
