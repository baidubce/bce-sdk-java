package com.baidubce.services.as.model.asgroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.as.model.zone.ZoneInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xucongyang on 2018/06/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupCreateRequest extends AbstractBceRequest {

    @JsonIgnore
    private String clientToken;

    private String groupName;

    private List<ZoneInfo> zoneInfo;

    private GroupConfig config;

    private List<BlbInfo> blb;

    private NodeInfo node;

    private List<CdsInfo> cds;

    private EipInfo eip;

    private BillingInfo billing = new BillingInfo();

    private List<String> rds;

    private List<String> scs;

    private HealthCheckConfig healthCheck;

    private String expansionStrategy = ExpansionStrategyType.Priority.name();

    private String shrinkageStrategy = ShrinkageStrategyType.Earlier.name();

    private AssignTagInfo assignTagInfo;

    private String keypairId = "";

    private String keypairName = "";

    private List<NodeInfo> nodes;

    private CmdConfig cmdConfig;

    private BccNameConfig bccNameConfig;

    public String getKeypairId() {
        return keypairId;
    }

    public void setKeypairId(String keypairId) {
        this.keypairId = keypairId;
    }

    public String getKeypairName() {
        return keypairName;
    }

    public void setKeypairName(String keypairName) {
        this.keypairName = keypairName;
    }

    public List<NodeInfo> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeInfo> nodes) {
        this.nodes = nodes;
    }

    public CmdConfig getCmdConfig() {
        return cmdConfig;
    }

    public void setCmdConfig(CmdConfig cmdConfig) {
        this.cmdConfig = cmdConfig;
    }

    public BccNameConfig getBccNameConfig() {
        return bccNameConfig;
    }

    public void setBccNameConfig(BccNameConfig bccNameConfig) {
        this.bccNameConfig = bccNameConfig;
    }

    public AssignTagInfo getAssignTagInfo() {
        return assignTagInfo;
    }

    public void setAssignTagInfo(AssignTagInfo assignTagInfo) {
        this.assignTagInfo = assignTagInfo;
    }

    public List<String> getScs() {
        return scs;
    }

    public void setScs(List<String> scs) {
        this.scs = scs;
    }

    public List<String> getRds() {
        return rds;
    }

    public void setRds(List<String> rds) {
        this.rds = rds;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ZoneInfo> getZoneInfo() {
        return zoneInfo;
    }

    public void setZoneInfo(List<ZoneInfo> zoneInfo) {
        this.zoneInfo = zoneInfo;
    }

    public GroupConfig getConfig() {
        return config;
    }

    public void setConfig(GroupConfig config) {
        this.config = config;
    }

    public List<BlbInfo> getBlb() {
        return blb;
    }

    public void setBlb(List<BlbInfo> blb) {
        this.blb = blb;
    }

    public NodeInfo getNode() {
        return node;
    }

    public void setNode(NodeInfo node) {
        this.node = node;
    }

    public List<CdsInfo> getCds() {
        return cds;
    }

    public void setCds(List<CdsInfo> cds) {
        this.cds = cds;
    }

    public EipInfo getEip() {
        return eip;
    }

    public void setEip(EipInfo eip) {
        this.eip = eip;
    }

    public BillingInfo getBilling() {
        return billing;
    }

    public void setBilling(BillingInfo billing) {
        this.billing = billing;
    }

    public HealthCheckConfig getHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(HealthCheckConfig healthCheck) {
        this.healthCheck = healthCheck;
    }

    public String getExpansionStrategy() {
        return expansionStrategy;
    }

    public void setExpansionStrategy(String expansionStrategy) {
        this.expansionStrategy = expansionStrategy;
    }

    public String getShrinkageStrategy() {
        return shrinkageStrategy;
    }

    public void setShrinkageStrategy(String shrinkageStrategy) {
        this.shrinkageStrategy = shrinkageStrategy;
    }
    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GroupCreateRequest{");
        sb.append("groupName='").append(groupName).append('\'');
        sb.append(", zoneInfo=").append(zoneInfo);
        sb.append(", config=").append(config);
        sb.append(", blb=").append(blb);
        sb.append(", node=").append(node);
        sb.append(", assignTagInfo=").append(assignTagInfo);
        sb.append(", cds=").append(cds);
        sb.append(", eip=").append(eip);
        sb.append(", billing=").append(billing);
        sb.append(", rds=").append(rds);
        sb.append(", scs=").append(scs);
        sb.append(", healthCheck=").append(healthCheck);
        sb.append(", expansionStrategy=").append(expansionStrategy);
        sb.append(", shrinkageStrategy=").append(shrinkageStrategy);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
