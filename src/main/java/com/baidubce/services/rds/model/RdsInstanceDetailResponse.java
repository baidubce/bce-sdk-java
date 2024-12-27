package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

/**
 * The response of rds instance detail
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsInstanceDetailResponse extends AbstractBceResponse {

    private String instanceId;
    private String instanceShortId;
    private String instanceName;
    private RdsEngine engine;
    private String engineVersion;
    private String rdsMinorVersion;
    private String characterSetName;
    private RdsEndpoint endpoint;
    private String instanceClass;
    private Integer allocatedMemoryInMB;
    private double allocatedMemoryInGB;
    private double memoryCapacity;
    private Integer allocatedStorageInGB;
    private Integer volumeCapacity;
    private Integer totalStorageInGB;
    private Integer usedStorageInMB;
    private double usedStorageInGB;
    private double usedStorage;
    private Integer cpuCount;
    private RdsInstanceType instanceType;
    private String sourceInstanceId;
    private Integer readReplicaNum;
    private List<String> readReplica;
    private String instanceStatus;
    private String lockMode;
    private String eipStatus;
    private String publicAccessStatus;
    private String superUserFlag;
    private String replicationType;
    private String syncMode;
    private RdsBlbService blbService;
    private boolean publiclyAccessible;
    private String instanceCreateTime;
    private String instanceExpireTime;
    private String productType;
    private String paymentTiming;
    private RdsTopology topology;
    private String azone;
    private String vpcId;
    private Map subnetId;
    private String region;
    private String sourceRegion;
    private String billingStatus;
    private String applicationType;
    private Integer nodeAmount;
    private Integer oldInstance;
    private Integer onlineStatus;
    private boolean isSingle;
    private List<RdsDccHostInfo> dccHostIds;
    private String machineType; // 和API类型不一致
    private Integer weight;
    private String nodeType = "";
    private DateTime repairStartTime;
    private DateTime repairEndTime;
    private String quotStatus;
    private RdsRoleInfo nodeReadReplica;
    private RdsRoleInfo nodeMaster;
    private RdsRoleInfo nodeSlave;
    private RdsRoleInfo nodeProxy;
    private String diskIoType;
    private String groupId;
    private String groupName;
    private String tdeStatus;
    private String userId;
    private String bgwGroupId;
    private boolean bgwGroupExclusive;
    private List<RoGroup> roGroupList;
    private boolean roGroupAbnormal;
    private String diskType;
    private String cdsType;
    private String maintainStartTime;
    private Integer maintainDuration;
    private String maintainPeriod;
    private String instanceSubStatus;
    private Integer autoResizeDisk;
    private String edgeRegion;
    private String edgeRegionName;
    private String edgeVpcId;
    private String edgeSubnetId;
    private Integer haStrategy;
    private String location;
    private boolean hasSlave;
    private Integer expireDate;
    private boolean hasProxy;
    private String vpcName;
    private String resourceUuid;
    private List<RdsTag> tags;
    private String task;
    private String orderStatus;
    private String vpcCidr;
    private List<String> zoneNames;
    private List<MachinePO> dccHosts;
    private String category;
    private String name;
    private RdsResourceGroupsDetailFull resourceGroup;
    private String resourceGroupId;
    private String resourceGroupName;

    private RdsBackupPolicy backupPolicy;
    private List<RdsSubnet> subnets;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceShortId() {
        return instanceShortId;
    }

    public void setInstanceShortId(String instanceShortId) {
        this.instanceShortId = instanceShortId;
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

    public String getRdsMinorVersion() {
        return rdsMinorVersion;
    }

    public void setRdsMinorVersion(String rdsMinorVersion) {
        this.rdsMinorVersion = rdsMinorVersion;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public RdsEndpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(RdsEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public String getInstanceClass() {
        return instanceClass;
    }

    public void setInstanceClass(String instanceClass) {
        this.instanceClass = instanceClass;
    }

    public Integer getAllocatedMemoryInMB() {
        return allocatedMemoryInMB;
    }

    public void setAllocatedMemoryInMB(Integer allocatedMemoryInMB) {
        this.allocatedMemoryInMB = allocatedMemoryInMB;
    }

    public double getAllocatedMemoryInGB() {
        return allocatedMemoryInGB;
    }

    public void setAllocatedMemoryInGB(double allocatedMemoryInGB) {
        this.allocatedMemoryInGB = allocatedMemoryInGB;
    }

    public double getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(double memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public Integer getAllocatedStorageInGB() {
        return allocatedStorageInGB;
    }

    public void setAllocatedStorageInGB(Integer allocatedStorageInGB) {
        this.allocatedStorageInGB = allocatedStorageInGB;
    }

    public Integer getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(Integer volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public Integer getTotalStorageInGB() {
        return totalStorageInGB;
    }

    public void setTotalStorageInGB(Integer totalStorageInGB) {
        this.totalStorageInGB = totalStorageInGB;
    }

    public Integer getUsedStorageInMB() {
        return usedStorageInMB;
    }

    public void setUsedStorageInMB(Integer usedStorageInMB) {
        this.usedStorageInMB = usedStorageInMB;
    }

    public double getUsedStorageInGB() {
        return usedStorageInGB;
    }

    public void setUsedStorageInGB(double usedStorageInGB) {
        this.usedStorageInGB = usedStorageInGB;
    }

    public double getUsedStorage() {
        return usedStorage;
    }

    public void setUsedStorage(double usedStorage) {
        this.usedStorage = usedStorage;
    }

    public Integer getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Integer cpuCount) {
        this.cpuCount = cpuCount;
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

    public Integer getReadReplicaNum() {
        return readReplicaNum;
    }

    public void setReadReplicaNum(Integer readReplicaNum) {
        this.readReplicaNum = readReplicaNum;
    }

    public List<String> getReadReplica() {
        return readReplica;
    }

    public void setReadReplica(List<String> readReplica) {
        this.readReplica = readReplica;
    }

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public String getLockMode() {
        return lockMode;
    }

    public void setLockMode(String lockMode) {
        this.lockMode = lockMode;
    }

    public String getEipStatus() {
        return eipStatus;
    }

    public void setEipStatus(String eipStatus) {
        this.eipStatus = eipStatus;
    }

    public String getPublicAccessStatus() {
        return publicAccessStatus;
    }

    public void setPublicAccessStatus(String publicAccessStatus) {
        this.publicAccessStatus = publicAccessStatus;
    }

    public String getSuperUserFlag() {
        return superUserFlag;
    }

    public void setSuperUserFlag(String superUserFlag) {
        this.superUserFlag = superUserFlag;
    }

    public String getReplicationType() {
        return replicationType;
    }

    public void setReplicationType(String replicationType) {
        this.replicationType = replicationType;
    }

    public String getSyncMode() {
        return syncMode;
    }

    public void setSyncMode(String syncMode) {
        this.syncMode = syncMode;
    }

    public RdsBlbService getBlbService() {
        return blbService;
    }

    public void setBlbService(RdsBlbService blbService) {
        this.blbService = blbService;
    }

    public boolean isPubliclyAccessible() {
        return publiclyAccessible;
    }

    public void setPubliclyAccessible(boolean publiclyAccessible) {
        this.publiclyAccessible = publiclyAccessible;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public RdsTopology getTopology() {
        return topology;
    }

    public void setTopology(RdsTopology topology) {
        this.topology = topology;
    }

    public String getAzone() {
        return azone;
    }

    public void setAzone(String azone) {
        this.azone = azone;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public Map getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(Map subnetId) {
        this.subnetId = subnetId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSourceRegion() {
        return sourceRegion;
    }

    public void setSourceRegion(String sourceRegion) {
        this.sourceRegion = sourceRegion;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Integer getNodeAmount() {
        return nodeAmount;
    }

    public void setNodeAmount(Integer nodeAmount) {
        this.nodeAmount = nodeAmount;
    }

    public Integer getOldInstance() {
        return oldInstance;
    }

    public void setOldInstance(Integer oldInstance) {
        this.oldInstance = oldInstance;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public List<RdsDccHostInfo> getDccHostIds() {
        return dccHostIds;
    }

    public void setDccHostIds(List<RdsDccHostInfo> dccHostIds) {
        this.dccHostIds = dccHostIds;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public DateTime getRepairStartTime() {
        return repairStartTime;
    }

    public void setRepairStartTime(DateTime repairStartTime) {
        this.repairStartTime = repairStartTime;
    }

    public DateTime getRepairEndTime() {
        return repairEndTime;
    }

    public void setRepairEndTime(DateTime repairEndTime) {
        this.repairEndTime = repairEndTime;
    }

    public String getQuotStatus() {
        return quotStatus;
    }

    public void setQuotStatus(String quotStatus) {
        this.quotStatus = quotStatus;
    }

    public RdsRoleInfo getNodeReadReplica() {
        return nodeReadReplica;
    }

    public void setNodeReadReplica(RdsRoleInfo nodeReadReplica) {
        this.nodeReadReplica = nodeReadReplica;
    }

    public RdsRoleInfo getNodeMaster() {
        return nodeMaster;
    }

    public void setNodeMaster(RdsRoleInfo nodeMaster) {
        this.nodeMaster = nodeMaster;
    }

    public RdsRoleInfo getNodeSlave() {
        return nodeSlave;
    }

    public void setNodeSlave(RdsRoleInfo nodeSlave) {
        this.nodeSlave = nodeSlave;
    }

    public RdsRoleInfo getNodeProxy() {
        return nodeProxy;
    }

    public void setNodeProxy(RdsRoleInfo nodeProxy) {
        this.nodeProxy = nodeProxy;
    }

    public String getDiskIoType() {
        return diskIoType;
    }

    public void setDiskIoType(String diskIoType) {
        this.diskIoType = diskIoType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTdeStatus() {
        return tdeStatus;
    }

    public void setTdeStatus(String tdeStatus) {
        this.tdeStatus = tdeStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBgwGroupId() {
        return bgwGroupId;
    }

    public void setBgwGroupId(String bgwGroupId) {
        this.bgwGroupId = bgwGroupId;
    }

    public boolean isBgwGroupExclusive() {
        return bgwGroupExclusive;
    }

    public void setBgwGroupExclusive(boolean bgwGroupExclusive) {
        this.bgwGroupExclusive = bgwGroupExclusive;
    }

    public List<RoGroup> getRoGroupList() {
        return roGroupList;
    }

    public void setRoGroupList(List<RoGroup> roGroupList) {
        this.roGroupList = roGroupList;
    }

    public boolean isRoGroupAbnormal() {
        return roGroupAbnormal;
    }

    public void setRoGroupAbnormal(boolean roGroupAbnormal) {
        this.roGroupAbnormal = roGroupAbnormal;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getCdsType() {
        return cdsType;
    }

    public void setCdsType(String cdsType) {
        this.cdsType = cdsType;
    }

    public String getMaintainStartTime() {
        return maintainStartTime;
    }

    public void setMaintainStartTime(String maintainStartTime) {
        this.maintainStartTime = maintainStartTime;
    }

    public Integer getMaintainDuration() {
        return maintainDuration;
    }

    public void setMaintainDuration(Integer maintainDuration) {
        this.maintainDuration = maintainDuration;
    }

    public String getMaintainPeriod() {
        return maintainPeriod;
    }

    public void setMaintainPeriod(String maintainPeriod) {
        this.maintainPeriod = maintainPeriod;
    }

    public String getInstanceSubStatus() {
        return instanceSubStatus;
    }

    public void setInstanceSubStatus(String instanceSubStatus) {
        this.instanceSubStatus = instanceSubStatus;
    }

    public Integer getAutoResizeDisk() {
        return autoResizeDisk;
    }

    public void setAutoResizeDisk(Integer autoResizeDisk) {
        this.autoResizeDisk = autoResizeDisk;
    }

    public String getEdgeRegion() {
        return edgeRegion;
    }

    public void setEdgeRegion(String edgeRegion) {
        this.edgeRegion = edgeRegion;
    }

    public String getEdgeRegionName() {
        return edgeRegionName;
    }

    public void setEdgeRegionName(String edgeRegionName) {
        this.edgeRegionName = edgeRegionName;
    }

    public String getEdgeVpcId() {
        return edgeVpcId;
    }

    public void setEdgeVpcId(String edgeVpcId) {
        this.edgeVpcId = edgeVpcId;
    }

    public String getEdgeSubnetId() {
        return edgeSubnetId;
    }

    public void setEdgeSubnetId(String edgeSubnetId) {
        this.edgeSubnetId = edgeSubnetId;
    }

    public Integer getHaStrategy() {
        return haStrategy;
    }

    public void setHaStrategy(Integer haStrategy) {
        this.haStrategy = haStrategy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isHasSlave() {
        return hasSlave;
    }

    public void setHasSlave(boolean hasSlave) {
        this.hasSlave = hasSlave;
    }

    public Integer getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Integer expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isHasProxy() {
        return hasProxy;
    }

    public void setHasProxy(boolean hasProxy) {
        this.hasProxy = hasProxy;
    }

    public String getVpcName() {
        return vpcName;
    }

    public void setVpcName(String vpcName) {
        this.vpcName = vpcName;
    }

    public String getResourceUuid() {
        return resourceUuid;
    }

    public void setResourceUuid(String resourceUuid) {
        this.resourceUuid = resourceUuid;
    }

    public List<RdsTag> getTags() {
        return tags;
    }

    public void setTags(List<RdsTag> tags) {
        this.tags = tags;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getVpcCidr() {
        return vpcCidr;
    }

    public void setVpcCidr(String vpcCidr) {
        this.vpcCidr = vpcCidr;
    }

    public List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }

    public List<MachinePO> getDccHosts() {
        return dccHosts;
    }

    public void setDccHosts(List<MachinePO> dccHosts) {
        this.dccHosts = dccHosts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RdsResourceGroupsDetailFull getResourceGroup() {
        return resourceGroup;
    }

    public void setResourceGroup(RdsResourceGroupsDetailFull resourceGroup) {
        this.resourceGroup = resourceGroup;
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

    public RdsBackupPolicy getBackupPolicy() {
        return backupPolicy;
    }

    public void setBackupPolicy(RdsBackupPolicy backupPolicy) {
        this.backupPolicy = backupPolicy;
    }

    public List<RdsSubnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<RdsSubnet> subnets) {
        this.subnets = subnets;
    }
}
