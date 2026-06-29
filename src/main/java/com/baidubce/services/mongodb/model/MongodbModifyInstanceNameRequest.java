package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request for modifying a MongoDB instance name.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbModifyInstanceNameRequest extends GenericMongodbRequest {
    private String dbInstanceId;

    private String dbInstanceName;

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getDbInstanceName() {
        return dbInstanceName;
    }

    public void setDbInstanceName(String dbInstanceName) {
        this.dbInstanceName = dbInstanceName;
    }
}
