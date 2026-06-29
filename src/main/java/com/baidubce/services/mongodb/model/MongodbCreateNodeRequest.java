package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request for adding a component (mongos / shard) to a sharded cluster.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbCreateNodeRequest extends GenericMongodbRequest {

    private String dbInstanceId;

    private int nodeCpuCount;

    private int nodeMemoryCapacity;

    private int nodeStorage;

    private String nodeType;

    private int maxConns;

    private int purchaseCount = 1;

    private String nodeStorageType;

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
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

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public int getMaxConns() {
        return maxConns;
    }

    public void setMaxConns(int maxConns) {
        this.maxConns = maxConns;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getNodeStorageType() {
        return nodeStorageType;
    }

    public void setNodeStorageType(String nodeStorageType) {
        this.nodeStorageType = nodeStorageType;
    }
}
