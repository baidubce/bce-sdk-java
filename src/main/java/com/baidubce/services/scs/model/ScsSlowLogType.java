package com.baidubce.services.scs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Scs slow log type
 */
public enum ScsSlowLogType {
    Proxy("proxy"),
    Redis("redis"),
    Unknown("unknown");

    private final String value;

    ScsSlowLogType(String value) {
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
    public static ScsSlowLogType create(String value) {
        if (Proxy.value.equalsIgnoreCase(value)) {
            return Proxy;
        } else if (Redis.value.equalsIgnoreCase(value)) {
            return Redis;
        } else {
            return Unknown;
        }
    }
}
