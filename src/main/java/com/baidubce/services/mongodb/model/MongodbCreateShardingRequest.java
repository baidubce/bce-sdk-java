package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Request for creating a MongoDB sharded cluster instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbCreateShardingRequest extends GenericMongodbRequest {

    private MongodbBilling billing = new MongodbBilling();

    private int purchaseCount = 1;

    private String dbInstanceName;

    private String storageEngine;

    private String engineVersion;

    private String dbInstanceType;

    private int mongosCount;

    private int mongosCpuCount;

    private int mongosMemoryCapacity;

    private int shardCount;

    private int shardCpuCount;

    private int shardMemoryCapacity;

    private int shardStorage;

    private String shardStorageType;

    private String accountPassword;

    private String srcDbInstanceId;

    private String backupId;

    private String vpcId;

    private List<MongodbSubnetMap> subnets;

    private String restoreTime;

    private List<MongodbTag> tags;

    private String resGroupId;

    public MongodbBilling getBilling() {
        return billing;
    }

    public void setBilling(MongodbBilling billing) {
        this.billing = billing;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getDbInstanceName() {
        return dbInstanceName;
    }

    public void setDbInstanceName(String dbInstanceName) {
        this.dbInstanceName = dbInstanceName;
    }

    public String getStorageEngine() {
        return storageEngine;
    }

    public void setStorageEngine(String storageEngine) {
        this.storageEngine = storageEngine;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getDbInstanceType() {
        return dbInstanceType;
    }

    public void setDbInstanceType(String dbInstanceType) {
        this.dbInstanceType = dbInstanceType;
    }

    public int getMongosCount() {
        return mongosCount;
    }

    public void setMongosCount(int mongosCount) {
        this.mongosCount = mongosCount;
    }

    public int getMongosCpuCount() {
        return mongosCpuCount;
    }

    public void setMongosCpuCount(int mongosCpuCount) {
        this.mongosCpuCount = mongosCpuCount;
    }

    public int getMongosMemoryCapacity() {
        return mongosMemoryCapacity;
    }

    public void setMongosMemoryCapacity(int mongosMemoryCapacity) {
        this.mongosMemoryCapacity = mongosMemoryCapacity;
    }

    public int getShardCount() {
        return shardCount;
    }

    public void setShardCount(int shardCount) {
        this.shardCount = shardCount;
    }

    public int getShardCpuCount() {
        return shardCpuCount;
    }

    public void setShardCpuCount(int shardCpuCount) {
        this.shardCpuCount = shardCpuCount;
    }

    public int getShardMemoryCapacity() {
        return shardMemoryCapacity;
    }

    public void setShardMemoryCapacity(int shardMemoryCapacity) {
        this.shardMemoryCapacity = shardMemoryCapacity;
    }

    public int getShardStorage() {
        return shardStorage;
    }

    public void setShardStorage(int shardStorage) {
        this.shardStorage = shardStorage;
    }

    public String getShardStorageType() {
        return shardStorageType;
    }

    public void setShardStorageType(String shardStorageType) {
        this.shardStorageType = shardStorageType;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getSrcDbInstanceId() {
        return srcDbInstanceId;
    }

    public void setSrcDbInstanceId(String srcDbInstanceId) {
        this.srcDbInstanceId = srcDbInstanceId;
    }

    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public List<MongodbSubnetMap> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<MongodbSubnetMap> subnets) {
        this.subnets = subnets;
    }

    public String getRestoreTime() {
        return restoreTime;
    }

    public void setRestoreTime(String restoreTime) {
        this.restoreTime = restoreTime;
    }

    public List<MongodbTag> getTags() {
        return tags;
    }

    public void setTags(List<MongodbTag> tags) {
        this.tags = tags;
    }

    public String getResGroupId() {
        return resGroupId;
    }

    public void setResGroupId(String resGroupId) {
        this.resGroupId = resGroupId;
    }
}
