package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * A simplified view of a newly created MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbInstanceIdView {

    private String dbInstanceUUID;

    private String dbInstanceId;

    private String connectionString;

    private String readonlyNodeConnectionString;

    private String port;

    private List<String> bindBcmMemberIds;

    public String getDbInstanceUUID() {
        return dbInstanceUUID;
    }

    public void setDbInstanceUUID(String dbInstanceUUID) {
        this.dbInstanceUUID = dbInstanceUUID;
    }

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getReadonlyNodeConnectionString() {
        return readonlyNodeConnectionString;
    }

    public void setReadonlyNodeConnectionString(String readonlyNodeConnectionString) {
        this.readonlyNodeConnectionString = readonlyNodeConnectionString;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<String> getBindBcmMemberIds() {
        return bindBcmMemberIds;
    }

    public void setBindBcmMemberIds(List<String> bindBcmMemberIds) {
        this.bindBcmMemberIds = bindBcmMemberIds;
    }
}
