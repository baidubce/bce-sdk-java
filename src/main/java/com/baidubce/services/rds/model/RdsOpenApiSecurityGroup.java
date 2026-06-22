package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Security group bound to a rds instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RdsOpenApiSecurityGroup {

    private String securityGroupRemark;

    private String projectId;

    private String securityGroupName;

    private String securityGroupId;

    private String securityGroupUuid;

    private String tenantId;

    private String vpcId;

    private String vpcName;

    private List<RdsSecurityGroupRule> outbound;

    private List<RdsSecurityGroupRule> inbound;

    private List<RdsSecurityGroupRule> rules;

    public String getSecurityGroupRemark() {
        return securityGroupRemark;
    }

    public void setSecurityGroupRemark(String securityGroupRemark) {
        this.securityGroupRemark = securityGroupRemark;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSecurityGroupName() {
        return securityGroupName;
    }

    public void setSecurityGroupName(String securityGroupName) {
        this.securityGroupName = securityGroupName;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(String securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    public String getSecurityGroupUuid() {
        return securityGroupUuid;
    }

    public void setSecurityGroupUuid(String securityGroupUuid) {
        this.securityGroupUuid = securityGroupUuid;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getVpcName() {
        return vpcName;
    }

    public void setVpcName(String vpcName) {
        this.vpcName = vpcName;
    }

    public List<RdsSecurityGroupRule> getOutbound() {
        return outbound;
    }

    public void setOutbound(List<RdsSecurityGroupRule> outbound) {
        this.outbound = outbound;
    }

    public List<RdsSecurityGroupRule> getInbound() {
        return inbound;
    }

    public void setInbound(List<RdsSecurityGroupRule> inbound) {
        this.inbound = inbound;
    }

    public List<RdsSecurityGroupRule> getRules() {
        return rules;
    }

    public void setRules(List<RdsSecurityGroupRule> rules) {
        this.rules = rules;
    }
}
