package com.baidubce.services.scs.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

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
    private int storeType;
    private int purchaseCount = 1;
    private int shardNum = 1;
    private Integer proxyNum;
    private ScsClusterType clusterType;
    private int replicationNum = 2;
    private String vpcId;
    private List<ScsSubnet> subnets;
    private String autoRenewTimeUnit;
    private Integer autoRenewTime;

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

    public int getReplicationNum() {
        return replicationNum;
    }

    public void setReplicationNum(int replicationNum) {
        this.replicationNum = replicationNum;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public List<ScsSubnet> getSubnets() {
        return subnets;
    }

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
