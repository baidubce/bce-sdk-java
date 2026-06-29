package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Request for creating a MongoDB replica set instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbCreateReplicaRequest extends GenericMongodbRequest {

    private MongodbBilling billing = new MongodbBilling();

    private int purchaseCount = 1;

    private String dbInstanceName;

    private String storageEngine;

    private String engineVersion;

    private String dbInstanceType;

    private int dbInstanceCpuCount;

    private int dbInstanceMemoryCapacity;

    private int dbInstanceStorage;

    private String accountPassword;

    private String dbInstanceStorageType;

    private int votingMemberNum;

    private int readonlyNodeNum;

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

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getDbInstanceStorageType() {
        return dbInstanceStorageType;
    }

    public void setDbInstanceStorageType(String dbInstanceStorageType) {
        this.dbInstanceStorageType = dbInstanceStorageType;
    }

    public int getVotingMemberNum() {
        return votingMemberNum;
    }

    public void setVotingMemberNum(int votingMemberNum) {
        this.votingMemberNum = votingMemberNum;
    }

    public int getReadonlyNodeNum() {
        return readonlyNodeNum;
    }

    public void setReadonlyNodeNum(int readonlyNodeNum) {
        this.readonlyNodeNum = readonlyNodeNum;
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
