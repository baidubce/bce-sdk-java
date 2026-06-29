package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * MongoDB instance detail.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbInstance extends GenericMongodbResponse {
    private String dbInstanceId;

    private String dbInstanceUUID;

    private String dbInstanceName;

    private String connectionString;

    private String readOnlyNodeConnectionString;

    private String ipv6ConnectionString;

    private boolean supportIpv6;

    private String port;

    private String engineVersion;

    private String storageEngine;

    private String dbInstanceType;

    private int dbInstanceCpuCount;

    private int dbInstanceMemoryCapacity;

    private int dbInstanceStorage;

    private String dbInstanceStorageType;

    private int maxConns;

    private int readonlyNodeNum;

    private int votingMemberNum;

    private String dbInstanceStatus;

    private String vpcId;

    private String deploymentMode;

    private int mongosCount;

    private int shardCount;

    private String publicAccessStatus;

    private String paymentTiming;

    private List<MongodbTag> tags;

    private List<MongodbSubnetMap> subnets;

    private Date createTime;

    private Date expireTime;

    private List<MongodbBackupPolicy> backupPolicy;

    public String getDbInstanceId() {
        return dbInstanceId;
    }

    public void setDbInstanceId(String dbInstanceId) {
        this.dbInstanceId = dbInstanceId;
    }

    public String getDbInstanceUUID() {
        return dbInstanceUUID;
    }

    public void setDbInstanceUUID(String dbInstanceUUID) {
        this.dbInstanceUUID = dbInstanceUUID;
    }

    public String getDbInstanceName() {
        return dbInstanceName;
    }

    public void setDbInstanceName(String dbInstanceName) {
        this.dbInstanceName = dbInstanceName;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getReadOnlyNodeConnectionString() {
        return readOnlyNodeConnectionString;
    }

    public void setReadOnlyNodeConnectionString(String readOnlyNodeConnectionString) {
        this.readOnlyNodeConnectionString = readOnlyNodeConnectionString;
    }

    public String getIpv6ConnectionString() {
        return ipv6ConnectionString;
    }

    public void setIpv6ConnectionString(String ipv6ConnectionString) {
        this.ipv6ConnectionString = ipv6ConnectionString;
    }

    public boolean getSupportIpv6() {
        return supportIpv6;
    }

    public void setSupportIpv6(boolean supportIpv6) {
        this.supportIpv6 = supportIpv6;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getStorageEngine() {
        return storageEngine;
    }

    public void setStorageEngine(String storageEngine) {
        this.storageEngine = storageEngine;
    }

    public String getDbInstanceType() {
        return dbInstanceType;
    }

    public void setDbInstanceType(String dbInstanceType) {
        this.dbInstanceType = dbInstanceType;
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

    public String getDbInstanceStorageType() {
        return dbInstanceStorageType;
    }

    public void setDbInstanceStorageType(String dbInstanceStorageType) {
        this.dbInstanceStorageType = dbInstanceStorageType;
    }

    public int getMaxConns() {
        return maxConns;
    }

    public void setMaxConns(int maxConns) {
        this.maxConns = maxConns;
    }

    public int getReadonlyNodeNum() {
        return readonlyNodeNum;
    }

    public void setReadonlyNodeNum(int readonlyNodeNum) {
        this.readonlyNodeNum = readonlyNodeNum;
    }

    public int getVotingMemberNum() {
        return votingMemberNum;
    }

    public void setVotingMemberNum(int votingMemberNum) {
        this.votingMemberNum = votingMemberNum;
    }

    public String getDbInstanceStatus() {
        return dbInstanceStatus;
    }

    public void setDbInstanceStatus(String dbInstanceStatus) {
        this.dbInstanceStatus = dbInstanceStatus;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getDeploymentMode() {
        return deploymentMode;
    }

    public void setDeploymentMode(String deploymentMode) {
        this.deploymentMode = deploymentMode;
    }

    public int getMongosCount() {
        return mongosCount;
    }

    public void setMongosCount(int mongosCount) {
        this.mongosCount = mongosCount;
    }

    public int getShardCount() {
        return shardCount;
    }

    public void setShardCount(int shardCount) {
        this.shardCount = shardCount;
    }

    public String getPublicAccessStatus() {
        return publicAccessStatus;
    }

    public void setPublicAccessStatus(String publicAccessStatus) {
        this.publicAccessStatus = publicAccessStatus;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public List<MongodbTag> getTags() {
        return tags;
    }

    public void setTags(List<MongodbTag> tags) {
        this.tags = tags;
    }

    public List<MongodbSubnetMap> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<MongodbSubnetMap> subnets) {
        this.subnets = subnets;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public List<MongodbBackupPolicy> getBackupPolicy() {
        return backupPolicy;
    }

    public void setBackupPolicy(List<MongodbBackupPolicy> backupPolicy) {
        this.backupPolicy = backupPolicy;
    }
}
