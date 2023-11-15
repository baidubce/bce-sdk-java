package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Request to create rds instance
 */
public class RdsCreateInstanceRequest extends AbstractBceRequest {

    private String clientToken;
    private RdsBilling billing;
    /**
     * purchase the count of rds instance
     * default count is 1
     */
    private int purchaseCount = 1;

    private String instanceName;
    private RdsEngine engine;
    private String engineVersion;
    private String category;

    private Integer cpuCount;
    private Integer memoryCapacity;
    private Integer volumeCapacity;
    private String diskIoType;

    private List<String> zoneNames;
    private String vpcId;
    private boolean isDirectPay;
    private List<RdsSubnet> subnets;
    private List<RdsTag> tags;
    private String characterSetName;
    private RdsRenewTimeUnit autoRenewTimeUnit;
    private Integer autoRenewTime;

    public String getDiskIoType() {
        return diskIoType;
    }

    public void setDiskIoType(String diskIoType) {
        this.diskIoType = diskIoType;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public RdsBilling getBilling() {
        return billing;
    }

    public void setBilling(RdsBilling billing) {
        this.billing = billing;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
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

    public List<RdsTag> getTags() {
        return tags;
    }

    public void setTags(List<RdsTag> tags) {
        this.tags = tags;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public RdsRenewTimeUnit getAutoRenewTimeUnit() {
        return autoRenewTimeUnit;
    }

    public void setAutoRenewTimeUnit(RdsRenewTimeUnit autoRenewTimeUnit) {
        this.autoRenewTimeUnit = autoRenewTimeUnit;
    }

    public Integer getAutoRenewTime() {
        return autoRenewTime;
    }

    public void setAutoRenewTime(Integer autoRenewTime) {
        this.autoRenewTime = autoRenewTime;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
