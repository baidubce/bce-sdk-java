package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Create rds readable instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsCreateReadableInstance extends AbstractBceRequest {

    private String clientToken;
    private String sourceInstanceId;
    private RdsBilling billing;

    private Integer purchaseCount = 1;
    private String instanceName;

    private Integer cpuCount;
    private Integer memoryCapacity;
    private Integer volumeCapacity;
    private String diskIoType;

    private List<String> zoneNames;
    private String vpcId;
    private String bcmGroupName;
    private boolean isDirectPay;
    private List<RdsSubnet> subnets;
    private String ovip;
    private Integer entryPort;
    private String resourceGroupId;
    private String replicaType;
    private boolean isInheritMasterAuthip;
    private List<RdsTag> tags;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getSourceInstanceId() {
        return sourceInstanceId;
    }

    public void setSourceInstanceId(String sourceInstanceId) {
        this.sourceInstanceId = sourceInstanceId;
    }

    public RdsBilling getBilling() {
        return billing;
    }

    public void setBilling(RdsBilling billing) {
        this.billing = billing;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
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

    public String getDiskIoType() {
        return diskIoType;
    }

    public void setDiskIoType(String diskIoType) {
        this.diskIoType = diskIoType;
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

    public String getBcmGroupName() {
        return bcmGroupName;
    }

    public void setBcmGroupName(String bcmGroupName) {
        this.bcmGroupName = bcmGroupName;
    }

    public boolean getIsDirectPay() {
        return isDirectPay;
    }

    public void setIsDirectPay(boolean isDirectPay) {
        this.isDirectPay = isDirectPay;
    }

    public List<RdsSubnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<RdsSubnet> subnets) {
        this.subnets = subnets;
    }

    public String getOvip() {
        return ovip;
    }

    public void setOvip(String ovip) {
        this.ovip = ovip;
    }

    public Integer getEntryPort() {
        return entryPort;
    }

    public void setEntryPort(Integer entryPort) {
        this.entryPort = entryPort;
    }

    public String getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(String resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public String getReplicaType() {
        return replicaType;
    }

    public void setReplicaType(String replicaType) {
        this.replicaType = replicaType;
    }

    public boolean getIsInheritMasterAuthip() {
        return isInheritMasterAuthip;
    }

    public void setIsInheritMasterAuthip(boolean isInheritMasterAuthip) {
        this.isInheritMasterAuthip = isInheritMasterAuthip;
    }
    public List<RdsTag> getTags() {
        return tags;
    }

    public void setTags(List<RdsTag> tags) {
        this.tags = tags;
    }
}
