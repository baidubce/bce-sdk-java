package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request for resizing a sharded cluster component (mongos or shard).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbResizeNodeRequest extends GenericMongodbRequest {

    private String dbInstanceId;

    private String nodeId;

    private int nodeCpuCount;

    private int nodeMemoryCapacity;

    private int nodeStorage;

    private int maxConns;

    private String executionMode;

    public MongodbResizeNodeRequest() {
    }

    public MongodbResizeNodeRequest(String dbInstanceId, String nodeId, int nodeCpuCount, int nodeMemoryCapacity,
            int nodeStorage) {
        this.dbInstanceId = dbInstanceId;
        this.nodeId = nodeId;
        this.nodeCpuCount = nodeCpuCount;
        this.nodeMemoryCapacity = nodeMemoryCapacity;
        this.nodeStorage = nodeStorage;
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

    public int getNodeCpuCount() {
        return nodeCpuCount;
    }

    public void setNodeCpuCount(int nodeCpuCount) {
        this.nodeCpuCount = nodeCpuCount;
    }

    public int getNodeMemoryCapacity() {
        return nodeMemoryCapacity;
    }

    public void setNodeMemoryCapacity(int nodeMemoryCapacity) {
        this.nodeMemoryCapacity = nodeMemoryCapacity;
    }

    public int getNodeStorage() {
        return nodeStorage;
    }

    public void setNodeStorage(int nodeStorage) {
        this.nodeStorage = nodeStorage;
    }

    public int getMaxConns() {
        return maxConns;
    }

    public void setMaxConns(int maxConns) {
        this.maxConns = maxConns;
    }

    public String getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }
}
