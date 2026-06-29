package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Request for resizing a replica set MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbInstanceResizeRequest extends GenericMongodbRequest {

    private String dbInstanceId;

    private int dbInstanceCpuCount;

    private int dbInstanceMemoryCapacity;

    private int dbInstanceStorage;

    private int readonlyNodeNum;

    private int maxConns;

    private int votingMemberNum;

    private MongodbSubnetMap subnet;

    private String readonlyMemberToDelete;

    private String executionMode;

    private java.util.List<Long> bcmInstanceGroupIds;

    public MongodbInstanceResizeRequest() {
    }

    public MongodbInstanceResizeRequest(String dbInstanceId, int dbInstanceCpuCount, int dbInstanceMemoryCapacity,
            int dbInstanceStorage) {
        this.dbInstanceId = dbInstanceId;
        this.dbInstanceCpuCount = dbInstanceCpuCount;
        this.dbInstanceMemoryCapacity = dbInstanceMemoryCapacity;
        this.dbInstanceStorage = dbInstanceStorage;
    }

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public int getDbInstanceCpuCount() {
        return dbInstanceCpuCount;
    }

    public void setDbInstanceCpuCount(int dbInstanceCpuCount) {
        this.dbInstanceCpuCount = dbInstanceCpuCount;
    }

    public int getDbInstanceMemoryCapacity() {
        return dbInstanceMemoryCapacity;
    }

    public void setDbInstanceMemoryCapacity(int dbInstanceMemoryCapacity) {
        this.dbInstanceMemoryCapacity = dbInstanceMemoryCapacity;
    }

    public int getDbInstanceStorage() {
        return dbInstanceStorage;
    }

    public void setDbInstanceStorage(int dbInstanceStorage) {
        this.dbInstanceStorage = dbInstanceStorage;
    }

    public int getReadonlyNodeNum() {
        return readonlyNodeNum;
    }

    public void setReadonlyNodeNum(int readonlyNodeNum) {
        this.readonlyNodeNum = readonlyNodeNum;
    }

    public int getMaxConns() {
        return maxConns;
    }

    public void setMaxConns(int maxConns) {
        this.maxConns = maxConns;
    }

    public int getVotingMemberNum() {
        return votingMemberNum;
    }

    public void setVotingMemberNum(int votingMemberNum) {
        this.votingMemberNum = votingMemberNum;
    }

    public MongodbSubnetMap getSubnet() {
        return subnet;
    }

    public void setSubnet(MongodbSubnetMap subnet) {
        this.subnet = subnet;
    }

    public String getReadonlyMemberToDelete() {
        return readonlyMemberToDelete;
    }

    public void setReadonlyMemberToDelete(String readonlyMemberToDelete) {
        this.readonlyMemberToDelete = readonlyMemberToDelete;
    }

    public String getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }

    public java.util.List<Long> getBcmInstanceGroupIds() {
        return bcmInstanceGroupIds;
    }

    public void setBcmInstanceGroupIds(java.util.List<Long> bcmInstanceGroupIds) {
        this.bcmInstanceGroupIds = bcmInstanceGroupIds;
    }
}
