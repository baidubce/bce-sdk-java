package com.baidubce.services.gaiadb.model;

import java.util.List;
import java.util.Map;

public class ClusterCreateRequest extends GenericGaiadbRequest {
    private InstanceParam instanceParam;
    private Integer duration;
    private String productType;
    private Integer number = 1;
    private String autoRenewTimeUnit = "month";
    private int autoRenewTime = 0;
    private Boolean isDirectPay;

    public InstanceParam getInstanceParam() {
        return instanceParam;
    }

    public void setInstanceParam(InstanceParam instanceParam) {
        this.instanceParam = instanceParam;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAutoRenewTimeUnit() {
        return autoRenewTimeUnit;
    }

    public void setAutoRenewTimeUnit(String autoRenewTimeUnit) {
        this.autoRenewTimeUnit = autoRenewTimeUnit;
    }

    public int getAutoRenewTime() {
        return autoRenewTime;
    }

    public void setAutoRenewTime(int autoRenewTime) {
        this.autoRenewTime = autoRenewTime;
    }

    public Boolean getIsDirectPay() {
        return isDirectPay;
    }

    public void setIsDirectPay(Boolean directPay) {
        isDirectPay = directPay;
    }

    public static class InstanceParam {
        private String releaseVersion;
        private String diskType;
        private Integer allocatedMemoryInMB;
        private Integer allocatedStorageInGB;
        private Integer allocatedCpuInCore;
        private Integer instanceAmount;
        private Integer proxyAmount;
        private String azone;
        private String azoneMode;
        private String azoneBak;
        private String azoneBakMode;
        private String vpcId;
        private String subnetId;
        private List<Map<String, Object>> subnets;
        private Integer allocatedMemoryInGB;
        private String createSource;
        private String clusterId;
        private String srcClusterId;
        private String snapshotId;
        private Boolean isBackupCopy;
        private String pit;
        private String bmiType;
        private String multiActiveGroupId;
        private String multiActiveGroupRole;
        private Integer lowerCaseTableNames;
        private String computeTplId;
        private String interfaceTplId;
        private List<Map<String, Object>> tags;
        private String resourceGroupId;
        private String clusterClass;
        private String instanceType;
        private List<Map<String, Object>> syncInfo;
        private String resourceType;
        private Integer sliceAmount;
        private Integer proxyAllocatedCpuInCore;
        private Integer proxyAllocatedMemoryInGB;
        private String clusterName;
        private Map<String, Object> initialData;
        private String migrationId;
        private Integer port;

        public String getReleaseVersion() {
            return releaseVersion;
        }

        public void setReleaseVersion(String releaseVersion) {
            this.releaseVersion = releaseVersion;
        }

        public String getDiskType() {
            return diskType;
        }

        public void setDiskType(String diskType) {
            this.diskType = diskType;
        }

        public Integer getAllocatedMemoryInMB() {
            return allocatedMemoryInMB;
        }

        public void setAllocatedMemoryInMB(Integer allocatedMemoryInMB) {
            this.allocatedMemoryInMB = allocatedMemoryInMB;
        }

        public Integer getAllocatedStorageInGB() {
            return allocatedStorageInGB;
        }

        public void setAllocatedStorageInGB(Integer allocatedStorageInGB) {
            this.allocatedStorageInGB = allocatedStorageInGB;
        }

        public Integer getAllocatedCpuInCore() {
            return allocatedCpuInCore;
        }

        public void setAllocatedCpuInCore(Integer allocatedCpuInCore) {
            this.allocatedCpuInCore = allocatedCpuInCore;
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

        public String getVpcId() {
            return vpcId;
        }

        public void setVpcId(String vpcId) {
            this.vpcId = vpcId;
        }

        public String getSubnetId() {
            return subnetId;
        }

        public void setSubnetId(String subnetId) {
            this.subnetId = subnetId;
        }

        public List<Map<String, Object>> getSubnets() {
            return subnets;
        }

        public void setSubnets(List<Map<String, Object>> subnets) {
            this.subnets = subnets;
        }

        public Integer getAllocatedMemoryInGB() {
            return allocatedMemoryInGB;
        }

        public void setAllocatedMemoryInGB(Integer allocatedMemoryInGB) {
            this.allocatedMemoryInGB = allocatedMemoryInGB;
        }

        public String getCreateSource() {
            return createSource;
        }

        public void setCreateSource(String createSource) {
            this.createSource = createSource;
        }

        public String getClusterId() {
            return clusterId;
        }

        public void setClusterId(String clusterId) {
            this.clusterId = clusterId;
        }

        public String getSrcClusterId() {
            return srcClusterId;
        }

        public void setSrcClusterId(String srcClusterId) {
            this.srcClusterId = srcClusterId;
        }

        public String getSnapshotId() {
            return snapshotId;
        }

        public void setSnapshotId(String snapshotId) {
            this.snapshotId = snapshotId;
        }

        public Boolean getIsBackupCopy() {
            return isBackupCopy;
        }

        public void setIsBackupCopy(Boolean backupCopy) {
            isBackupCopy = backupCopy;
        }

        public String getPit() {
            return pit;
        }

        public void setPit(String pit) {
            this.pit = pit;
        }

        public String getBmiType() {
            return bmiType;
        }

        public void setBmiType(String bmiType) {
            this.bmiType = bmiType;
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

        public Integer getLowerCaseTableNames() {
            return lowerCaseTableNames;
        }

        public void setLowerCaseTableNames(Integer lowerCaseTableNames) {
            this.lowerCaseTableNames = lowerCaseTableNames;
        }

        public String getComputeTplId() {
            return computeTplId;
        }

        public void setComputeTplId(String computeTplId) {
            this.computeTplId = computeTplId;
        }

        public String getInterfaceTplId() {
            return interfaceTplId;
        }

        public void setInterfaceTplId(String interfaceTplId) {
            this.interfaceTplId = interfaceTplId;
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

        public String getClusterClass() {
            return clusterClass;
        }

        public void setClusterClass(String clusterClass) {
            this.clusterClass = clusterClass;
        }

        public String getInstanceType() {
            return instanceType;
        }

        public void setInstanceType(String instanceType) {
            this.instanceType = instanceType;
        }

        public List<Map<String, Object>> getSyncInfo() {
            return syncInfo;
        }

        public void setSyncInfo(List<Map<String, Object>> syncInfo) {
            this.syncInfo = syncInfo;
        }

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
        }

        public Integer getSliceAmount() {
            return sliceAmount;
        }

        public void setSliceAmount(Integer sliceAmount) {
            this.sliceAmount = sliceAmount;
        }

        public Integer getProxyAllocatedCpuInCore() {
            return proxyAllocatedCpuInCore;
        }

        public void setProxyAllocatedCpuInCore(Integer proxyAllocatedCpuInCore) {
            this.proxyAllocatedCpuInCore = proxyAllocatedCpuInCore;
        }

        public Integer getProxyAllocatedMemoryInGB() {
            return proxyAllocatedMemoryInGB;
        }

        public void setProxyAllocatedMemoryInGB(Integer proxyAllocatedMemoryInGB) {
            this.proxyAllocatedMemoryInGB = proxyAllocatedMemoryInGB;
        }

        public String getClusterName() {
            return clusterName;
        }

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }

        public Map<String, Object> getInitialData() {
            return initialData;
        }

        public void setInitialData(Map<String, Object> initialData) {
            this.initialData = initialData;
        }

        public String getMigrationId() {
            return migrationId;
        }

        public void setMigrationId(String migrationId) {
            this.migrationId = migrationId;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }
}
