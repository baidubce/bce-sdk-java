package com.baidubce.services.gaiadb.model;

import java.util.List;
import java.util.Map;

public class ClusterDetailResponse extends GenericGaiadbResponse {
    private String clusterId;
    private String clusterName;
    private Integer enableColumnar;
    private Integer supportColumnar;
    private Boolean multiEntranceEnabled;
    private Map<String, Object> endpoint;
    private String instanceClass;
    private String instanceClassDesc;
    private Integer allocatedCpuInCore;
    private Long allocatedMemoryInMB;
    private Long allocatedStorageInGB;
    private Long usedStorageInMB;
    private String instanceStatus;
    private String instanceStatusDesc;
    private String lockMode;
    private String eipStatus;
    private Boolean publiclyAccessible;
    private String instanceCreateTime;
    private String instanceExpireTime;
    private String azone;
    private String azoneMode;
    private String azoneBak;
    private String azoneBakMode;
    private String azoneDesc;
    private String releaseVersion;
    private String instanceType;
    private Object olapClusters;
    private Object srcClusters;
    private Object apserviceList;
    private String diskType;
    private String compatibilityDesc;
    private String vpcId;
    private String region;
    private Integer instanceAmount;
    private Integer proxyAmount;
    private Map<String, Object> subnetId;
    private String productType;
    private Integer isPay;
    private List<String> zoneNames;
    private String multiActiveGroupId;
    private String multiActiveGroupRole;
    private String multiActiveGroupName;
    private Integer supportAuthipConf;
    private Integer supportSuperUser;
    private Boolean scaleOutFlag;
    private Long expireDate;
    private Map<String, Object> autoRenewRule;
    private Object orderStatus;
    private List<Map<String, Object>> tags;
    private String resourceGroupId;
    private String resourceGroupName;
    private String clusterClass;
    private String clusterClassDesc;
    private Map<String, Object> serviceStatus;
    private String resourceType;
    private List<Map<String, Object>> slices;
    private Integer distributedProxyAmount;
    private Map<String, Object> distributedProxyFlavor;
    private List<Map<String, Object>> clusterIpList;
    private List<Map<String, Object>> clusterEipList;
    private List<Map<String, Object>> clusterDnsList;
    private String writeStatus;
    private String migrationId;
    private Integer supportModifyNetwork;
    private String flavorType;
    private Integer supportServerless;
    private Integer supportScaleToZero;
    private Map<String, Object> serverlessConfig;
    private Integer supportBackupCopy;
    private List<Map<String, Object>> computeList;
    private List<Map<String, Object>> proxyList;
    private List<Map<String, Object>> lbInfo;
    private String vpcName;
    private String vpcCidr;
    private String expireTime;
    private List<Map<String, Object>> subnets;
    private String endTime;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Integer getEnableColumnar() {
        return enableColumnar;
    }

    public void setEnableColumnar(Integer enableColumnar) {
        this.enableColumnar = enableColumnar;
    }

    public Integer getSupportColumnar() {
        return supportColumnar;
    }

    public void setSupportColumnar(Integer supportColumnar) {
        this.supportColumnar = supportColumnar;
    }

    public Boolean getMultiEntranceEnabled() {
        return multiEntranceEnabled;
    }

    public void setMultiEntranceEnabled(Boolean multiEntranceEnabled) {
        this.multiEntranceEnabled = multiEntranceEnabled;
    }

    public Map<String, Object> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Map<String, Object> endpoint) {
        this.endpoint = endpoint;
    }

    public String getInstanceClass() {
        return instanceClass;
    }

    public void setInstanceClass(String instanceClass) {
        this.instanceClass = instanceClass;
    }

    public String getInstanceClassDesc() {
        return instanceClassDesc;
    }

    public void setInstanceClassDesc(String instanceClassDesc) {
        this.instanceClassDesc = instanceClassDesc;
    }

    public Integer getAllocatedCpuInCore() {
        return allocatedCpuInCore;
    }

    public void setAllocatedCpuInCore(Integer allocatedCpuInCore) {
        this.allocatedCpuInCore = allocatedCpuInCore;
    }

    public Long getAllocatedMemoryInMB() {
        return allocatedMemoryInMB;
    }

    public void setAllocatedMemoryInMB(Long allocatedMemoryInMB) {
        this.allocatedMemoryInMB = allocatedMemoryInMB;
    }

    public Long getAllocatedStorageInGB() {
        return allocatedStorageInGB;
    }

    public void setAllocatedStorageInGB(Long allocatedStorageInGB) {
        this.allocatedStorageInGB = allocatedStorageInGB;
    }

    public Long getUsedStorageInMB() {
        return usedStorageInMB;
    }

    public void setUsedStorageInMB(Long usedStorageInMB) {
        this.usedStorageInMB = usedStorageInMB;
    }

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public String getInstanceStatusDesc() {
        return instanceStatusDesc;
    }

    public void setInstanceStatusDesc(String instanceStatusDesc) {
        this.instanceStatusDesc = instanceStatusDesc;
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

    public Boolean getPubliclyAccessible() {
        return publiclyAccessible;
    }

    public void setPubliclyAccessible(Boolean publiclyAccessible) {
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

    public String getAzone() {
        return azone;
    }

    public void setAzone(String azone) {
        this.azone = azone;
    }

    public String getAzoneMode() {
        return azoneMode;
    }

    public void setAzoneMode(String azoneMode) {
        this.azoneMode = azoneMode;
    }

    public String getAzoneBak() {
        return azoneBak;
    }

    public void setAzoneBak(String azoneBak) {
        this.azoneBak = azoneBak;
    }

    public String getAzoneBakMode() {
        return azoneBakMode;
    }

    public void setAzoneBakMode(String azoneBakMode) {
        this.azoneBakMode = azoneBakMode;
    }

    public String getAzoneDesc() {
        return azoneDesc;
    }

    public void setAzoneDesc(String azoneDesc) {
        this.azoneDesc = azoneDesc;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public Object getOlapClusters() {
        return olapClusters;
    }

    public void setOlapClusters(Object olapClusters) {
        this.olapClusters = olapClusters;
    }

    public Object getSrcClusters() {
        return srcClusters;
    }

    public void setSrcClusters(Object srcClusters) {
        this.srcClusters = srcClusters;
    }

    public Object getApserviceList() {
        return apserviceList;
    }

    public void setApserviceList(Object apserviceList) {
        this.apserviceList = apserviceList;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getCompatibilityDesc() {
        return compatibilityDesc;
    }

    public void setCompatibilityDesc(String compatibilityDesc) {
        this.compatibilityDesc = compatibilityDesc;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getInstanceAmount() {
        return instanceAmount;
    }

    public void setInstanceAmount(Integer instanceAmount) {
        this.instanceAmount = instanceAmount;
    }

    public Integer getProxyAmount() {
        return proxyAmount;
    }

    public void setProxyAmount(Integer proxyAmount) {
        this.proxyAmount = proxyAmount;
    }

    public Map<String, Object> getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(Map<String, Object> subnetId) {
        this.subnetId = subnetId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }

    public String getMultiActiveGroupId() {
        return multiActiveGroupId;
    }

    public void setMultiActiveGroupId(String multiActiveGroupId) {
        this.multiActiveGroupId = multiActiveGroupId;
    }

    public String getMultiActiveGroupRole() {
        return multiActiveGroupRole;
    }

    public void setMultiActiveGroupRole(String multiActiveGroupRole) {
        this.multiActiveGroupRole = multiActiveGroupRole;
    }

    public String getMultiActiveGroupName() {
        return multiActiveGroupName;
    }

    public void setMultiActiveGroupName(String multiActiveGroupName) {
        this.multiActiveGroupName = multiActiveGroupName;
    }

    public Integer getSupportAuthipConf() {
        return supportAuthipConf;
    }

    public void setSupportAuthipConf(Integer supportAuthipConf) {
        this.supportAuthipConf = supportAuthipConf;
    }

    public Integer getSupportSuperUser() {
        return supportSuperUser;
    }

    public void setSupportSuperUser(Integer supportSuperUser) {
        this.supportSuperUser = supportSuperUser;
    }

    public Boolean getScaleOutFlag() {
        return scaleOutFlag;
    }

    public void setScaleOutFlag(Boolean scaleOutFlag) {
        this.scaleOutFlag = scaleOutFlag;
    }

    public Long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    public Map<String, Object> getAutoRenewRule() {
        return autoRenewRule;
    }

    public void setAutoRenewRule(Map<String, Object> autoRenewRule) {
        this.autoRenewRule = autoRenewRule;
    }

    public Object getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Object orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Map<String, Object>> getTags() {
        return tags;
    }

    public void setTags(List<Map<String, Object>> tags) {
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

    public String getClusterClass() {
        return clusterClass;
    }

    public void setClusterClass(String clusterClass) {
        this.clusterClass = clusterClass;
    }

    public String getClusterClassDesc() {
        return clusterClassDesc;
    }

    public void setClusterClassDesc(String clusterClassDesc) {
        this.clusterClassDesc = clusterClassDesc;
    }

    public Map<String, Object> getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Map<String, Object> serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public List<Map<String, Object>> getSlices() {
        return slices;
    }

    public void setSlices(List<Map<String, Object>> slices) {
        this.slices = slices;
    }

    public Integer getDistributedProxyAmount() {
        return distributedProxyAmount;
    }

    public void setDistributedProxyAmount(Integer distributedProxyAmount) {
        this.distributedProxyAmount = distributedProxyAmount;
    }

    public Map<String, Object> getDistributedProxyFlavor() {
        return distributedProxyFlavor;
    }

    public void setDistributedProxyFlavor(Map<String, Object> distributedProxyFlavor) {
        this.distributedProxyFlavor = distributedProxyFlavor;
    }

    public List<Map<String, Object>> getClusterIpList() {
        return clusterIpList;
    }

    public void setClusterIpList(List<Map<String, Object>> clusterIpList) {
        this.clusterIpList = clusterIpList;
    }

    public List<Map<String, Object>> getClusterEipList() {
        return clusterEipList;
    }

    public void setClusterEipList(List<Map<String, Object>> clusterEipList) {
        this.clusterEipList = clusterEipList;
    }

    public List<Map<String, Object>> getClusterDnsList() {
        return clusterDnsList;
    }

    public void setClusterDnsList(List<Map<String, Object>> clusterDnsList) {
        this.clusterDnsList = clusterDnsList;
    }

    public String getWriteStatus() {
        return writeStatus;
    }

    public void setWriteStatus(String writeStatus) {
        this.writeStatus = writeStatus;
    }

    public String getMigrationId() {
        return migrationId;
    }

    public void setMigrationId(String migrationId) {
        this.migrationId = migrationId;
    }

    public Integer getSupportModifyNetwork() {
        return supportModifyNetwork;
    }

    public void setSupportModifyNetwork(Integer supportModifyNetwork) {
        this.supportModifyNetwork = supportModifyNetwork;
    }

    public String getFlavorType() {
        return flavorType;
    }

    public void setFlavorType(String flavorType) {
        this.flavorType = flavorType;
    }

    public Integer getSupportServerless() {
        return supportServerless;
    }

    public void setSupportServerless(Integer supportServerless) {
        this.supportServerless = supportServerless;
    }

    public Integer getSupportScaleToZero() {
        return supportScaleToZero;
    }

    public void setSupportScaleToZero(Integer supportScaleToZero) {
        this.supportScaleToZero = supportScaleToZero;
    }

    public Map<String, Object> getServerlessConfig() {
        return serverlessConfig;
    }

    public void setServerlessConfig(Map<String, Object> serverlessConfig) {
        this.serverlessConfig = serverlessConfig;
    }

    public Integer getSupportBackupCopy() {
        return supportBackupCopy;
    }

    public void setSupportBackupCopy(Integer supportBackupCopy) {
        this.supportBackupCopy = supportBackupCopy;
    }

    public List<Map<String, Object>> getComputeList() {
        return computeList;
    }

    public void setComputeList(List<Map<String, Object>> computeList) {
        this.computeList = computeList;
    }

    public List<Map<String, Object>> getProxyList() {
        return proxyList;
    }

    public void setProxyList(List<Map<String, Object>> proxyList) {
        this.proxyList = proxyList;
    }

    public List<Map<String, Object>> getLbInfo() {
        return lbInfo;
    }

    public void setLbInfo(List<Map<String, Object>> lbInfo) {
        this.lbInfo = lbInfo;
    }

    public String getVpcName() {
        return vpcName;
    }

    public void setVpcName(String vpcName) {
        this.vpcName = vpcName;
    }

    public String getVpcCidr() {
        return vpcCidr;
    }

    public void setVpcCidr(String vpcCidr) {
        this.vpcCidr = vpcCidr;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public List<Map<String, Object>> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<Map<String, Object>> subnets) {
        this.subnets = subnets;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
