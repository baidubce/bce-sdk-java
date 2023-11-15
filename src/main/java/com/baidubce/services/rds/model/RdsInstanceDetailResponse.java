package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The response of rds instance detail
 */
public class RdsInstanceDetailResponse extends AbstractBceResponse {

    private String instanceId;
    private String instanceName;
    private RdsEngine engine;
    private String engineVersion;
    private String category;
    private String instanceStatus;
    private Integer cpuCount;
    private Integer memoryCapacity;
    private Integer volumeCapacity;
    private Integer nodeAmount;
    private Integer usedStorage;
    private Date instanceCreateTime;
    private Date instanceExpireTime;
    private RdsEndpoint endpoint;
    private String publicAccessStatus;
    private String syncMode;
    private RdsBackupPolicy backupPolicy;
    private String region;
    private RdsInstanceType instanceType;
    private String sourceInstanceId;
    private String sourceRegion;
    private List<String> zoneNames = new ArrayList<String>();
    private String vpcId;
    private List<RdsSubnet> subnets = new ArrayList<RdsSubnet>();
    private RdsTopology topology;
    private String paymentTiming;
    private String characterSetName;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public RdsEngine getEngine() {
        return engine;
    }

    public void setEngine(RdsEngine engine) {
        this.engine = engine;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public Integer getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Integer cpuCount) {
        this.cpuCount = cpuCount;
    }

    public Integer getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(Integer memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public Integer getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(Integer volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public Integer getNodeAmount() {
        return nodeAmount;
    }

    public void setNodeAmount(Integer nodeAmount) {
        this.nodeAmount = nodeAmount;
    }

    public Integer getUsedStorage() {
        return usedStorage;
    }

    public void setUsedStorage(Integer usedStorage) {
        this.usedStorage = usedStorage;
    }

    public Date getInstanceCreateTime() {
        return instanceCreateTime;
    }

    public void setInstanceCreateTime(Date instanceCreateTime) {
        this.instanceCreateTime = instanceCreateTime;
    }

    public Date getInstanceExpireTime() {
        return instanceExpireTime;
    }

    public void setInstanceExpireTime(Date instanceExpireTime) {
        this.instanceExpireTime = instanceExpireTime;
    }

    public RdsEndpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(RdsEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public String getPublicAccessStatus() {
        return publicAccessStatus;
    }

    public void setPublicAccessStatus(String publicAccessStatus) {
        this.publicAccessStatus = publicAccessStatus;
    }

    public String getSyncMode() {
        return syncMode;
    }

    public void setSyncMode(String syncMode) {
        this.syncMode = syncMode;
    }

    public RdsBackupPolicy getBackupPolicy() {
        return backupPolicy;
    }

    public void setBackupPolicy(RdsBackupPolicy backupPolicy) {
        this.backupPolicy = backupPolicy;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public RdsInstanceType getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(RdsInstanceType instanceType) {
        this.instanceType = instanceType;
    }

    public String getSourceInstanceId() {
        return sourceInstanceId;
    }

    public void setSourceInstanceId(String sourceInstanceId) {
        this.sourceInstanceId = sourceInstanceId;
    }

    public String getSourceRegion() {
        return sourceRegion;
    }

    public void setSourceRegion(String sourceRegion) {
        this.sourceRegion = sourceRegion;
    }

    public List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public List<RdsSubnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<RdsSubnet> subnets) {
        this.subnets = subnets;
    }

    public RdsTopology getTopology() {
        return topology;
    }

    public void setTopology(RdsTopology topology) {
        this.topology = topology;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }
}
