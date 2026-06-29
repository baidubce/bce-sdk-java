package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A MongoDB user role: a role granted on a specific database.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbRole {
    private String dbName;

    private String role;

    public MongodbRole() {
    }

    public MongodbRole(String dbName, String role) {
        this.dbName = dbName;
        this.role = role;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
