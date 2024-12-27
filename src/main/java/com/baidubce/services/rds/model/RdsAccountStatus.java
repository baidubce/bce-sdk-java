package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum RdsAccountStatus {
    Creating("Creating"),
    Available("Available"),
    Updating("Updating"),
    Deleting("Deleting"),
    Unknown("Unknown");

    private final String value;

    RdsAccountStatus(String value) {
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
    public static RdsAccountStatus create(String value) {
        if (Creating.value.equalsIgnoreCase(value)) {
            return Creating;
        } else if (Available.value.equalsIgnoreCase(value)) {
            return Available;
        } else if (Updating.value.equalsIgnoreCase(value)) {
            return Updating;
        } else if (Deleting.value.equalsIgnoreCase(value)) {
            return Deleting;
        } else {
            return Unknown;
        }
    }
}
