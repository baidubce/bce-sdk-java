package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Request to create rds instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsCreateInstanceRequest extends AbstractBceRequest {

    private String clientToken;
    private RdsBilling billing;

    private int purchaseCount = 1;

    private String instanceName;
    private RdsEngine engine;
    private String engineVersion;
    private String category;

    private Integer cpuCount;
    private Integer memoryCapacity;
    private Integer volumeCapacity;
    private String diskIoType;

    private List<String> zoneNames=new ArrayList<String>();
    private String bcmGroupName;
    private String vpcId;
    private boolean isDirectPay;
    private List<RdsSubnet> subnets;
    private List<RdsTag> tags;
    private String bgwGroupId;
    private boolean bgwGroupExclusive;
    private String characterSetName;
    private RdsRenewTimeUnit autoRenewTimeUnit;
    private Integer autoRenewTime;
    private Integer lowerCaseTableNames;
    private String parameterTemplateId;
    private String ovip;
    private Integer entryPort;
    private String replicationType;
    private String resourceGroupId;
    private RdsInitialDataReference initialDataReference;
    private List<RdsRecoveryToSourceInstanceModel> data;
    private String leaderInstanceId;


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

    public String getBcmGroupName() {
        return bcmGroupName;
    }

    public void setBcmGroupName(String bcmGroupName) {
        this.bcmGroupName = bcmGroupName;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public boolean setIsDirectPay(boolean isDirectPay) {
        return this.isDirectPay = isDirectPay;
    }

    public void setDirectPay(boolean directPay) {
        isDirectPay = directPay;
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

    public Integer getLowerCaseTableNames() {
        return lowerCaseTableNames;
    }

    public void setLowerCaseTableNames(Integer lowerCaseTableNames) {
        this.lowerCaseTableNames = lowerCaseTableNames;
    }

    public String getParameterTemplateId() {
        return parameterTemplateId;
    }

    public void setParameterTemplateId(String parameterTemplateId) {
        this.parameterTemplateId = parameterTemplateId;
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

    public String getReplicationType() {
        return replicationType;
    }

    public void setReplicationType(String replicationType) {
        this.replicationType = replicationType;
    }

    public String getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(String resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public RdsInitialDataReference getInitialDataReference() {
        return initialDataReference;
    }

    public void setInitialDataReference(RdsInitialDataReference initialDataReference) {
        this.initialDataReference = initialDataReference;
    }

    public List<RdsRecoveryToSourceInstanceModel> getData() {
        return data;
    }

    public void setData(List<RdsRecoveryToSourceInstanceModel> data) {
        this.data = data;
    }

    public String getLeaderInstanceId() {
        return leaderInstanceId;
    }

    public void setLeaderInstanceId(String leaderInstanceId) {
        this.leaderInstanceId = leaderInstanceId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
