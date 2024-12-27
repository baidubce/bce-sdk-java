package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Rds engine
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum RdsEngine {
    MySQL("MySQL"),
    SQLServer("SQLServer"),
    PostgreSQL("PostgreSQL"),
    Unknown("unknown");

    private String value;

    RdsEngine(String value) {
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
    public static RdsEngine instance(String value) {
        if (MySQL.value.equalsIgnoreCase(value)) {
            return MySQL;
        } else if (SQLServer.value.equalsIgnoreCase(value)) {
            return SQLServer;
        } else if (PostgreSQL.value.equalsIgnoreCase(value)) {
            return PostgreSQL;
        } else {
            return Unknown;
        }
    }
}
