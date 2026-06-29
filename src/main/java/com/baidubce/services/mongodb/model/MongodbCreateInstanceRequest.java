package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Request for creating a MongoDB instance (replica set or sharded cluster).
 *
 * <p>For a replica set, set {@code dbInstanceType} to the replica-set type and fill the
 * replica-set fields ({@code dbInstanceCpuCount}, {@code dbInstanceMemoryCapacity},
 * {@code dbInstanceStorage}, {@code votingMemberNum}, {@code maxConns}).
 *
 * <p>For a sharded cluster, fill the sharded fields ({@code mongosCpuCount}, {@code mongosCount},
 * {@code shardCpuCount}, {@code shardMemoryCapacity}, {@code shardStorage}, {@code shardCount},
 * {@code mongosMaxConns}).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbCreateInstanceRequest extends GenericMongodbRequest {

    private String dbInstanceName;

    private String engineVersion;

    private String storageEngine;

    private String dbInstanceType;

    // replica set fields
    private int dbInstanceCpuCount;
    private int dbInstanceMemoryCapacity;
    private int dbInstanceStorage;
    private String dbInstanceStorageType = "CDS_PREMIUM_SSD";
    private int votingMemberNum = 3;
    private int maxConns;

    // sharded cluster fields
    private int mongosCpuCount;
    private int mongosMemoryCapacity;
    private int mongosCount = 2;
    private int shardCpuCount;
    private int shardMemoryCapacity;
    private int shardStorage;
    private int shardCount = 2;
    private String shardStorageType = "LOCAL_DISK";
    private int mongosMaxConns;

    private int readonlyNodeNum;

    private String accountPassword;

    private String vpcId;

    private boolean supportIpv6;

    private List<MongodbSubnetMap> subnets;

    private MongodbBilling billing = new MongodbBilling();

    private int purchaseCount = 1;

    private List<MongodbTag> tags;

    private String deploymentMode;

    // ---- recovery parameters (used when creating an instance by restoring from a source instance) ----
    // source instance id to restore from
    private String srcDbInstanceId;

    // source region of the source instance
    private String srcRegion;

    // backup set id, used when restoring from a backup set
    private String backupId;

    // restore time point, used when restoring by time point
    private String restoreTime;

    // whether to restore at table/collection level
    private boolean tableRestore;

    // table-level restore items, used when tableRestore is true
    private List<MongodbTableRestoreItem> tableRestoreParams;

    public String getDbInstanceName() {
        return dbInstanceName;
    }

    public void setDbInstanceName(String dbInstanceName) {
        this.dbInstanceName = dbInstanceName;
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

    public int getVotingMemberNum() {
        return votingMemberNum;
    }

    public void setVotingMemberNum(int votingMemberNum) {
        this.votingMemberNum = votingMemberNum;
    }

    public int getMaxConns() {
        return maxConns;
    }

    public void setMaxConns(int maxConns) {
        this.maxConns = maxConns;
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

    public int getMongosCount() {
        return mongosCount;
    }

    public void setMongosCount(int mongosCount) {
        this.mongosCount = mongosCount;
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

    public int getShardCount() {
        return shardCount;
    }

    public void setShardCount(int shardCount) {
        this.shardCount = shardCount;
    }

    public String getShardStorageType() {
        return shardStorageType;
    }

    public void setShardStorageType(String shardStorageType) {
        this.shardStorageType = shardStorageType;
    }

    public int getMongosMaxConns() {
        return mongosMaxConns;
    }

    public void setMongosMaxConns(int mongosMaxConns) {
        this.mongosMaxConns = mongosMaxConns;
    }

    public int getReadonlyNodeNum() {
        return readonlyNodeNum;
    }

    public void setReadonlyNodeNum(int readonlyNodeNum) {
        this.readonlyNodeNum = readonlyNodeNum;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public boolean getSupportIpv6() {
        return supportIpv6;
    }

    public void setSupportIpv6(boolean supportIpv6) {
        this.supportIpv6 = supportIpv6;
    }

    public List<MongodbSubnetMap> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<MongodbSubnetMap> subnets) {
        this.subnets = subnets;
    }

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

    public List<MongodbTag> getTags() {
        return tags;
    }

    public void setTags(List<MongodbTag> tags) {
        this.tags = tags;
    }

    public String getDeploymentMode() {
        return deploymentMode;
    }

    public void setDeploymentMode(String deploymentMode) {
        this.deploymentMode = deploymentMode;
    }

    public String getSrcDbInstanceId() {
        return srcDbInstanceId;
    }

    public void setSrcDbInstanceId(String srcDbInstanceId) {
        this.srcDbInstanceId = srcDbInstanceId;
    }

    public String getSrcRegion() {
        return srcRegion;
    }

    public void setSrcRegion(String srcRegion) {
        this.srcRegion = srcRegion;
    }

    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }

    public String getRestoreTime() {
        return restoreTime;
    }

    public void setRestoreTime(String restoreTime) {
        this.restoreTime = restoreTime;
    }

    public boolean getTableRestore() {
        return tableRestore;
    }

    public void setTableRestore(boolean tableRestore) {
        this.tableRestore = tableRestore;
    }

    public List<MongodbTableRestoreItem> getTableRestoreParams() {
        return tableRestoreParams;
    }

    public void setTableRestoreParams(List<MongodbTableRestoreItem> tableRestoreParams) {
        this.tableRestoreParams = tableRestoreParams;
    }
}
