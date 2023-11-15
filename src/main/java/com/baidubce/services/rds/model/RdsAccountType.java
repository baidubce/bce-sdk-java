package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The account type of rds
 */
public enum RdsAccountType {

    Common("Common"),
    Super("Super"),
    Unknown("Unknown");
    private final String value;

    RdsAccountType(String value) {
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
    public static RdsAccountType create(String value) {
        if (Common.value.equalsIgnoreCase(value)) {
            return Common;
        } else if (Super.value.equalsIgnoreCase(value)) {
            return Super;
        } else {
            return Unknown;
        }
    }
}
