package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The privileges of rds database
 */
public class RdsDatabasePrivileges {

    @JsonProperty("dbName")
    private String databaseName;
    private String authType;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }
}
