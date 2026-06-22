package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Response of getting the enterprise security groups bound to a rds instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsInstanceEnterpriseSecurityDetail extends AbstractBceResponse {

    private List<EnterpriseSecurityGroup> enterpriseSecurityGroups;

    private List<EsgRule> activeEsgRules;

    private String instanceUuid;

    public List<EnterpriseSecurityGroup> getEnterpriseSecurityGroups() {
        return enterpriseSecurityGroups;
    }

    public void setEnterpriseSecurityGroups(List<EnterpriseSecurityGroup> enterpriseSecurityGroups) {
        this.enterpriseSecurityGroups = enterpriseSecurityGroups;
    }

    public List<EsgRule> getActiveEsgRules() {
        return activeEsgRules;
    }

    public void setActiveEsgRules(List<EsgRule> activeEsgRules) {
        this.activeEsgRules = activeEsgRules;
    }

    public String getInstanceUuid() {
        return instanceUuid;
    }

    public void setInstanceUuid(String instanceUuid) {
        this.instanceUuid = instanceUuid;
    }

    public static class EnterpriseSecurityGroup {

        private String esgId;

        private String esgUuid;

        private String name;

        private String desc;

        private int associateNum;

        private String createdTime;

        private String updatedTime;

        private Object tags;

        private List<EsgRule> rules;

        public String getEsgId() {
            return esgId;
        }

        public void setEsgId(String esgId) {
            this.esgId = esgId;
        }

        public String getEsgUuid() {
            return esgUuid;
        }

        public void setEsgUuid(String esgUuid) {
            this.esgUuid = esgUuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getAssociateNum() {
            return associateNum;
        }

        public void setAssociateNum(int associateNum) {
            this.associateNum = associateNum;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public List<EsgRule> getRules() {
            return rules;
        }

        public void setRules(List<EsgRule> rules) {
            this.rules = rules;
        }
    }

    public static class EsgRule {

        private String esgRuleId;

        private String esgRuleUuid;

        private String esgUuid;

        private String action;

        private String direction;

        private String ethertype;

        private String protocol;

        private String portRange;

        private String sourcePortRange;

        private int priority;

        private String remoteIpPrefix;

        private String localIpPrefix;

        private String ipCollectionUuid;

        private int ipCollectionType;

        private String remark;

        private String createdTime;

        private String updatedTime;

        public String getEsgRuleId() {
            return esgRuleId;
        }

        public void setEsgRuleId(String esgRuleId) {
            this.esgRuleId = esgRuleId;
        }

        public String getEsgRuleUuid() {
            return esgRuleUuid;
        }

        public void setEsgRuleUuid(String esgRuleUuid) {
            this.esgRuleUuid = esgRuleUuid;
        }

        public String getEsgUuid() {
            return esgUuid;
        }

        public void setEsgUuid(String esgUuid) {
            this.esgUuid = esgUuid;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
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

        public String getSourcePortRange() {
            return sourcePortRange;
        }

        public void setSourcePortRange(String sourcePortRange) {
            this.sourcePortRange = sourcePortRange;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getRemoteIpPrefix() {
            return remoteIpPrefix;
        }

        public void setRemoteIpPrefix(String remoteIpPrefix) {
            this.remoteIpPrefix = remoteIpPrefix;
        }

        public String getLocalIpPrefix() {
            return localIpPrefix;
        }

        public void setLocalIpPrefix(String localIpPrefix) {
            this.localIpPrefix = localIpPrefix;
        }

        public String getIpCollectionUuid() {
            return ipCollectionUuid;
        }

        public void setIpCollectionUuid(String ipCollectionUuid) {
            this.ipCollectionUuid = ipCollectionUuid;
        }

        public int getIpCollectionType() {
            return ipCollectionType;
        }

        public void setIpCollectionType(int ipCollectionType) {
            this.ipCollectionType = ipCollectionType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }
    }
}
