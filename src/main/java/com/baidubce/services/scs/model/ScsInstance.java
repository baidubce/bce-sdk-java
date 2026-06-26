package com.baidubce.services.scs.model;

/**
 * scs instance
 * {@link InstanceListResponse}
 */
public class ScsInstance {

    private String instanceId;
    private String instanceName;
    private String engine;
    private String engineVersion;
    private String characterSetName;
    private Integer volumeCapacity;
    private Double memoryCapacity;
    private Double usedStorage;
    private String instanceStatus;
    private String instanceCreateTime;
    private String instanceExpireTime;
    private Integer shardNum;
    private Integer replicationNum;
    private String clusterType;
    private String vnetIp;
    private String domain;
    private Integer port;
    private Double capacity;
    private Double usedCapacity;
    private String paymentTiming;
    private java.util.List<String> zoneNames;
    private java.util.List<ScsTag> tags;
    private String resourceGroupId;
    private String resourceGroupName;
    private java.util.List<String> deployIdList;
    private String orderStatus;
    private String nodeType;
    private Integer diskFlavor;
    private String diskType;
    private Integer storeType;
    private String eip;
    private String entrance;

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

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public Integer getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(Integer volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public Double getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(Double memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public Double getUsedStorage() {
        return usedStorage;
    }

    public void setUsedStorage(Double usedStorage) {
        this.usedStorage = usedStorage;
    }

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public String getInstanceCreateTime() {
        return instanceCreateTime;
    }

    public void setInstanceCreateTime(String instanceCreateTime) {
        this.instanceCreateTime = instanceCreateTime;
    }

    public String getInstanceExpireTime() {
        return instanceExpireTime;
    }

    public void setInstanceExpireTime(String instanceExpireTime) {
        this.instanceExpireTime = instanceExpireTime;
    }

    public Integer getShardNum() {
        return shardNum;
    }

    public void setShardNum(Integer shardNum) {
        this.shardNum = shardNum;
    }

    public Integer getReplicationNum() {
        return replicationNum;
    }

    public void setReplicationNum(Integer replicationNum) {
        this.replicationNum = replicationNum;
    }

    public String getClusterType() {
        return clusterType;
    }

    public void setClusterType(String clusterType) {
        this.clusterType = clusterType;
    }

    public String getVnetIp() {
        return vnetIp;
    }

    public void setVnetIp(String vnetIp) {
        this.vnetIp = vnetIp;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(Double usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public java.util.List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(java.util.List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }

    public java.util.List<ScsTag> getTags() {
        return tags;
    }

    public void setTags(java.util.List<ScsTag> tags) {
        this.tags = tags;
    }

    public String getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(String resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public String getResourceGroupName() {
        return resourceGroupName;
    }

    public void setResourceGroupName(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName;
    }

    public java.util.List<String> getDeployIdList() {
        return deployIdList;
    }

    public void setDeployIdList(java.util.List<String> deployIdList) {
        this.deployIdList = deployIdList;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getDiskFlavor() {
        return diskFlavor;
    }

    public void setDiskFlavor(Integer diskFlavor) {
        this.diskFlavor = diskFlavor;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getEip() {
        return eip;
    }

    public void setEip(String eip) {
        this.eip = eip;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    @Override
    public String toString() {
        return "ScsModel{" +
                "instanceId='" + instanceId + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", engine='" + engine + '\'' +
                ", engineVersion='" + engineVersion + '\'' +
                ", characterSetName='" + characterSetName + '\'' +
                ", volumeCapacity=" + volumeCapacity +
                ", memoryCapacity=" + memoryCapacity +
                ", usedStorage=" + usedStorage +
                ", instanceStatus='" + instanceStatus + '\'' +
                ", instanceCreateTime='" + instanceCreateTime + '\'' +
                ", instanceExpireTime='" + instanceExpireTime + '\'' +
                '}';
    }
}