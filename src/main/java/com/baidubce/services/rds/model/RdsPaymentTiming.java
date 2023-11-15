package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Rds payment type
 */
public enum RdsPaymentTiming {

    Prepaid("Prepaid"),
    Postpaid("Postpaid");

    private final String value;

    RdsPaymentTiming(String value) {
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
    public static RdsPaymentTiming create(String value) {
        if (Prepaid.value.equalsIgnoreCase(value)) {
            return Prepaid;
        } else {
            return Postpaid;
        }
    }
}
