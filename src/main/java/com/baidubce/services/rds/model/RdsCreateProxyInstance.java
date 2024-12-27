package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The request of create a proxy rds instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsCreateProxyInstance extends AbstractBceRequest {

    private String clientToken;
    private RdsBilling billing;
    private String sourceInstanceId;
    private String instanceName;
    private Integer nodeAmount;
    private List<String> zoneNames;
    private String vpcId;
    private boolean isDirectPay;
    private List<RdsSubnet> subnets;
    private String ovip;
    private Integer entryPort;
    private String resourceGroupId;
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

    public RdsBilling getBilling() {
        return billing;
    }

    public void setBilling(RdsBilling billing) {
        this.billing = billing;
    }

    public String getSourceInstanceId() {
        return sourceInstanceId;
    }

    public void setSourceInstanceId(String sourceInstanceId) {
        this.sourceInstanceId = sourceInstanceId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Integer getNodeAmount() {
        return nodeAmount;
    }

    public void setNodeAmount(Integer nodeAmount) {
        this.nodeAmount = nodeAmount;
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

    public List<RdsTag> getTags() {
        return tags;
    }

    public void setTags(List<RdsTag> tags) {
        this.tags = tags;
    }
}
