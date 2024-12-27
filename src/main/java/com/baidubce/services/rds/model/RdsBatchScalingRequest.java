package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The Request of Batch scaling  instances
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsBatchScalingRequest extends AbstractBceRequest {
    private String instanceId;
    private Integer cpuCount;
    private Integer memoryCapacity;
    private Integer volumeCapacity;
    private List<ReadReplicaRequest> readReplicas;
    private Boolean isBatchResize;
    private Boolean isDirectPay=false;
    private Boolean isEnhanced;
    private String effectiveTime="immediate";
    private String masterAzone;
    private String backupAzone;
    private String diskIoType;
    private String subnetId="";
    private String edgeSubnetId;
    private List<SubnetMap> subnets;
    private List<Long> couponId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
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

    public String getDiskIoType() {
        return diskIoType;
    }

    public void setDiskIoType(String diskIoType) {
        this.diskIoType = diskIoType;
    }

    public Integer getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(Integer volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public List<ReadReplicaRequest> getReadReplicas() {
        return readReplicas;
    }

    public void setReadReplicas(List<ReadReplicaRequest> readReplicas) {
        this.readReplicas = readReplicas;
    }

    public Boolean getBatchResize() {
        return isBatchResize;
    }

    public void setBatchResize(Boolean batchResize) {
        isBatchResize = batchResize;
    }

    public Boolean getDirectPay() {
        return isDirectPay;
    }

    public void setDirectPay(Boolean directPay) {
        isDirectPay = directPay;
    }

    public Boolean getEnhanced() {
        return isEnhanced;
    }

    public void setEnhanced(Boolean enhanced) {
        isEnhanced = enhanced;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getMasterAzone() {
        return masterAzone;
    }

    public void setMasterAzone(String masterAzone) {
        this.masterAzone = masterAzone;
    }

    public String getBackupAzone() {
        return backupAzone;
    }

    public void setBackupAzone(String backupAzone) {
        this.backupAzone = backupAzone;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getEdgeSubnetId() {
        return edgeSubnetId;
    }

    public void setEdgeSubnetId(String edgeSubnetId) {
        this.edgeSubnetId = edgeSubnetId;
    }

    public List<SubnetMap> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<SubnetMap> subnets) {
        this.subnets = subnets;
    }

    public List<Long> getCouponId() {
        return couponId;
    }

    public void setCouponId(List<Long> couponId) {
        this.couponId = couponId;
    }


    public static class ReadReplicaRequest {
        private String instanceId;
        private Integer cpuCount;
        private Integer memoryCapacity;
        private Integer volumeCapacity;
        private String diskIoType;

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public Integer getCpuCount() {
            return cpuCount;
        }

        public void setCpuCount(Integer cpuCount) {
            this.cpuCount = cpuCount;
        }

        public String getDiskIoType() {
            return diskIoType;
        }

        public void setDiskIoType(String diskIoType) {
            this.diskIoType = diskIoType;
        }

        public Integer getVolumeCapacity() {
            return volumeCapacity;
        }

        public void setVolumeCapacity(Integer volumeCapacity) {
            this.volumeCapacity = volumeCapacity;
        }

        public Integer getMemoryCapacity() {
            return memoryCapacity;
        }

        public void setMemoryCapacity(Integer memoryCapacity) {
            this.memoryCapacity = memoryCapacity;
        }
    }


    public static class SubnetMap {
        private String zoneName;
        private String subnetId;

        public String getZoneName() {
            return zoneName;
        }

        public void setZoneName(String zoneName) {
            this.zoneName = zoneName;
        }

        public String getSubnetId() {
            return subnetId;
        }

        public void setSubnetId(String subnetId) {
            this.subnetId = subnetId;
        }
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
