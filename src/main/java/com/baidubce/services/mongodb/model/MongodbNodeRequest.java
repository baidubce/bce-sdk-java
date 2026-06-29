package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request targeting a sharded cluster component (node), used by sharded master/slave switch.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbNodeRequest extends GenericMongodbRequest {

    private String dbInstanceId;

    private String nodeId;

    private String memberId;

    private String executionMode;

    public MongodbNodeRequest() {
    }

    public MongodbNodeRequest(String dbInstanceId, String nodeId) {
        this.dbInstanceId = dbInstanceId;
        this.nodeId = nodeId;
    }

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }
}
