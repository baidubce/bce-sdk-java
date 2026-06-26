package com.baidubce.services.scs.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.scs.model.instance.ScsBcmInstanceGroupRequest;
import com.baidubce.services.scs.model.instance.ScsReplication;

import java.util.List;

/**
 * Scs create instance request
 */
public class ScsCreateRequest extends AbstractBceRequest {

    private String clientToken;

    private ScsBilling billing;
    private String instanceName;
    private String nodeType;
    private int port;
    private String engineVersion;
    private Integer engine;
    private Integer diskFlavor;
    private String diskType;
    private int storeType;
    private int purchaseCount = 1;
    private int shardNum = 1;
    private Integer proxyNum;
    private ScsClusterType clusterType;
    /**
     * @deprecated use {@link #replicationInfo} instead.
     */
    @Deprecated
    private int replicationNum = 2;
    private List<ScsReplication> replicationInfo;
    private String vpcId;
    /**
     * @deprecated use {@link #replicationInfo} instead.
     */
    @Deprecated
    private List<ScsSubnet> subnets;
    private String autoRenewTimeUnit;
    private Integer autoRenewTime;
    private String bgwGroupId;
    private String resourceGroupId;
    private String confTpl;
    private Integer enableReadOnly;
    private List<ScsTag> tags;
    private List<ScsBcmInstanceGroupRequest> bcmInstanceGroups;
    private String autoBackupConfig;
    private List<String> deployIdList;

    private String clientAuth;

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public ScsBilling getBilling() {
        return billing;
    }

    public void setBilling(ScsBilling billing) {
        this.billing = billing;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public Integer getEngine() {
        return engine;
    }

    public void setEngine(Integer engine) {
        this.engine = engine;
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

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public int getShardNum() {
        return shardNum;
    }

    public void setShardNum(int shardNum) {
        this.shardNum = shardNum;
    }

    public Integer getProxyNum() {
        return proxyNum;
    }

    public void setProxyNum(Integer proxyNum) {
        this.proxyNum = proxyNum;
    }

    public ScsClusterType getClusterType() {
        return clusterType;
    }

    public void setClusterType(ScsClusterType clusterType) {
        this.clusterType = clusterType;
    }

    /**
     * @deprecated use {@link #getReplicationInfo()} instead.
     */
    @Deprecated
    public int getReplicationNum() {
        return replicationNum;
    }

    /**
     * @deprecated use {@link #setReplicationInfo(List)} instead.
     */
    @Deprecated
    public void setReplicationNum(int replicationNum) {
        this.replicationNum = replicationNum;
    }

    public List<ScsReplication> getReplicationInfo() {
        return replicationInfo;
    }

    public void setReplicationInfo(List<ScsReplication> replicationInfo) {
        this.replicationInfo = replicationInfo;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    /**
     * @deprecated use {@link #getReplicationInfo()} instead.
     */
    @Deprecated
    public List<ScsSubnet> getSubnets() {
        return subnets;
    }

    /**
     * @deprecated use {@link #setReplicationInfo(List)} instead.
     */
    @Deprecated
    public void setSubnets(List<ScsSubnet> subnets) {
        this.subnets = subnets;
    }

    public String getAutoRenewTimeUnit() {
        return autoRenewTimeUnit;
    }

    public void setAutoRenewTimeUnit(String autoRenewTimeUnit) {
        this.autoRenewTimeUnit = autoRenewTimeUnit;
    }

    public Integer getAutoRenewTime() {
        return autoRenewTime;
    }

    public void setAutoRenewTime(Integer autoRenewTime) {
        this.autoRenewTime = autoRenewTime;
    }

    public String getBgwGroupId() {
        return bgwGroupId;
    }

    public void setBgwGroupId(String bgwGroupId) {
        this.bgwGroupId = bgwGroupId;
    }

    public String getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(String resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public String getConfTpl() {
        return confTpl;
    }

    public void setConfTpl(String confTpl) {
        this.confTpl = confTpl;
    }

    public Integer getEnableReadOnly() {
        return enableReadOnly;
    }

    public void setEnableReadOnly(Integer enableReadOnly) {
        this.enableReadOnly = enableReadOnly;
    }

    public List<ScsTag> getTags() {
        return tags;
    }

    public void setTags(List<ScsTag> tags) {
        this.tags = tags;
    }

    public List<ScsBcmInstanceGroupRequest> getBcmInstanceGroups() {
        return bcmInstanceGroups;
    }

    public void setBcmInstanceGroups(List<ScsBcmInstanceGroupRequest> bcmInstanceGroups) {
        this.bcmInstanceGroups = bcmInstanceGroups;
    }

    public String getAutoBackupConfig() {
        return autoBackupConfig;
    }

    public void setAutoBackupConfig(String autoBackupConfig) {
        this.autoBackupConfig = autoBackupConfig;
    }

    public List<String> getDeployIdList() {
        return deployIdList;
    }

    public void setDeployIdList(List<String> deployIdList) {
        this.deployIdList = deployIdList;
    }

    public String getClientAuth() {
        return clientAuth;
    }

    public void setClientAuth(String clientAuth) {
        this.clientAuth = clientAuth;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
