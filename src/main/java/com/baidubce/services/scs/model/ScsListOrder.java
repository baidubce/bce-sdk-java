package com.baidubce.services.scs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Scs list order
 */
public enum ScsListOrder {
    Desc("desc"),
    Asc("aesc"),
    Unknown("unknown");

    private final String value;

    ScsListOrder(String value) {
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
    public static ScsListOrder create(String value) {
        if (Desc.value.equalsIgnoreCase(value)) {
            return Desc;
        } else if (Asc.value.equalsIgnoreCase(value)) {
            return Asc;
        } else {
            return Unknown;
        }
    }
}
