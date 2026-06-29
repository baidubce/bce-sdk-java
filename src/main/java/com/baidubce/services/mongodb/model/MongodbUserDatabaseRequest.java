package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Request for user and database management (create/drop user, update role,
 * create/drop database).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbUserDatabaseRequest extends GenericMongodbRequest {
    private String dbInstanceId;

    // user name or database name depending on the operation
    private String name;

    private String collectionName;

    private String description;

    private String password;

    private String plaintextPassword;

    private List<MongodbRole> roles;

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlaintextPassword() {
        return plaintextPassword;
    }

    public void setPlaintextPassword(String plaintextPassword) {
        this.plaintextPassword = plaintextPassword;
    }

    public List<MongodbRole> getRoles() {
        return roles;
    }

    public void setRoles(List<MongodbRole> roles) {
        this.roles = roles;
    }
}
