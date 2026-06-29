package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request targeting a replica set instance, used by replica-set master/slave switch.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbInstanceRequest extends GenericMongodbRequest {

    private String dbInstanceId;

    private String executionMode;

    public MongodbInstanceRequest() {
    }

    public MongodbInstanceRequest(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }
}
