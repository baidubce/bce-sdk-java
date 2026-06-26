package com.baidubce.services.scs.model.securitygroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsSecurityGroupVpcResponse extends AbstractBceResponse {
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public static class Group {
        private String securityGroupId;
        private String uuid;
        private String name;
        private String description;
        private String tenantId;
        private Integer associateNum;
        private String vpcId;
        private String vpcShortId;
        private String vpcName;
        private String createdTime;
        private Integer version;
        private Integer defaultSecurityGroup;

        public String getSecurityGroupId() {
            return securityGroupId;
        }

        public void setSecurityGroupId(String securityGroupId) {
            this.securityGroupId = securityGroupId;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public Integer getAssociateNum() {
            return associateNum;
        }

        public void setAssociateNum(Integer associateNum) {
            this.associateNum = associateNum;
        }

        public String getVpcId() {
            return vpcId;
        }

        public void setVpcId(String vpcId) {
            this.vpcId = vpcId;
        }

        public String getVpcShortId() {
            return vpcShortId;
        }

        public void setVpcShortId(String vpcShortId) {
            this.vpcShortId = vpcShortId;
        }

        public String getVpcName() {
            return vpcName;
        }

        public void setVpcName(String vpcName) {
            this.vpcName = vpcName;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public Integer getDefaultSecurityGroup() {
            return defaultSecurityGroup;
        }

        public void setDefaultSecurityGroup(Integer defaultSecurityGroup) {
            this.defaultSecurityGroup = defaultSecurityGroup;
        }
    }
}
