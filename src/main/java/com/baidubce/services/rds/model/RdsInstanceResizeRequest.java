package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The request of resize rds instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsInstanceResizeRequest extends AbstractBceRequest {

    private String instanceId;

    private Integer cpuCount;
    private Integer memoryCapacity;
    private Integer volumeCapacity;
    private Integer allocatedMemoryInMB;
    private Integer nodeAmount;
    private Boolean isDirectPay;
    private boolean isEnhanced;
    private String effectiveTime;
    private String masterAzone;
    private String backupAzone;
    private String diskIoType;
    private String subnetId;
    private String edgeSubnetId;
    private List<RdsBatchScalingRequest.SubnetMap> subnets;

    public Integer getAllocatedMemoryInMB() {
        return allocatedMemoryInMB;
    }

    public void setAllocatedMemoryInMB(Integer allocatedMemoryInMB) {
        this.allocatedMemoryInMB = allocatedMemoryInMB;
    }

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

    public Boolean getIsDirectPay() {
        return isDirectPay;
    }

    public void setIsDirectPay(Boolean isDirectPay) {
        this.isDirectPay = isDirectPay;
    }

    public boolean isEnhanced() {
        return isEnhanced;
    }

    public void setEnhanced(boolean enhanced) {
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

    public String getDiskIoType() {
        return diskIoType;
    }

    public void setDiskIoType(String diskIoType) {
        this.diskIoType = diskIoType;
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

    public List<RdsBatchScalingRequest.SubnetMap> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<RdsBatchScalingRequest.SubnetMap> subnets) {
        this.subnets = subnets;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
