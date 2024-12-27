package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Rds instance type
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum RdsInstanceType {
    Master("Master"),
    Financial("financial"),
    ReadReplica("ReadReplica"),
    RdsProxy("RdsProxy"),
    Unknown("unknown");

    private String value;

    RdsInstanceType(String value) {
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
    public static RdsInstanceType instance(String value) {
        if (Master.value.equalsIgnoreCase(value)) {
            return Master;
        } else if (Financial.value.equalsIgnoreCase(value)) {
            return Financial;
        } else if (ReadReplica.value.equalsIgnoreCase(value)) {
            return ReadReplica;
        } else if (RdsProxy.value.equalsIgnoreCase(value)) {
            return RdsProxy;
        } else {
            return Unknown;
        }
    }
}
