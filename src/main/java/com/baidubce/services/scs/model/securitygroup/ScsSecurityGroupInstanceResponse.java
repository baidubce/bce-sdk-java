package com.baidubce.services.scs.model.securitygroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsSecurityGroupInstanceResponse extends AbstractBceResponse {
    private List<Group> groups;
    private List<Rule> activeRules;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Rule> getActiveRules() {
        return activeRules;
    }

    public void setActiveRules(List<Rule> activeRules) {
        this.activeRules = activeRules;
    }

    public static class Group {
        private String securityGroupRemark;
        private String securityGroupName;
        private String securityGroupId;
        private String securityGroupUuid;
        private String vpcId;
        private String vpcName;
        private List<Rule> outbound;
        private List<Rule> inbound;
        private String projectId;

        public String getSecurityGroupRemark() {
            return securityGroupRemark;
        }

        public void setSecurityGroupRemark(String securityGroupRemark) {
            this.securityGroupRemark = securityGroupRemark;
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

        public List<Rule> getOutbound() {
            return outbound;
        }

        public void setOutbound(List<Rule> outbound) {
            this.outbound = outbound;
        }

        public List<Rule> getInbound() {
            return inbound;
        }

        public void setInbound(List<Rule> inbound) {
            this.inbound = inbound;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }
    }

    public static class Rule {
        private String tenantId;
        private String id;
        private String securityGroupRuleId;
        private String securityGroupId;
        private String securityGroupUuid;
        private String direction;
        private String ethertype;
        private String protocol;
        private String portRange;
        private String remoteGroupId;
        private String remoteGroupName;
        private String remoteIP;
        private String name;

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSecurityGroupRuleId() {
            return securityGroupRuleId;
        }

        public void setSecurityGroupRuleId(String securityGroupRuleId) {
            this.securityGroupRuleId = securityGroupRuleId;
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

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getEthertype() {
            return ethertype;
        }

        public void setEthertype(String ethertype) {
            this.ethertype = ethertype;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getPortRange() {
            return portRange;
        }

        public void setPortRange(String portRange) {
            this.portRange = portRange;
        }

        public String getRemoteGroupId() {
            return remoteGroupId;
        }

        public void setRemoteGroupId(String remoteGroupId) {
            this.remoteGroupId = remoteGroupId;
        }

        public String getRemoteGroupName() {
            return remoteGroupName;
        }

        public void setRemoteGroupName(String remoteGroupName) {
            this.remoteGroupName = remoteGroupName;
        }

        public String getRemoteIP() {
            return remoteIP;
        }

        public void setRemoteIP(String remoteIP) {
            this.remoteIP = remoteIP;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
