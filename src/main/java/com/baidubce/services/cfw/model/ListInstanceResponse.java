/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListInstanceResponse extends BaseBceResponse {
    /**
     * 标记查询的起始位置
     */
    private String marker;

    /**
     * true表示后面还有数据，false表示已经是最后一页
     */
    private Boolean isTruncated;

    /**
     * 获取下一页所需要传递的marker值。当isTruncated为false时，该域不出现
     */
    private String nextMarker;

    /**
     * 每页包含的最大数量
     */
    private Integer maxKeys;

    /**
     * 防护实例列表
     */
    private List<Instance> instances;

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setIsTruncated(Boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public Boolean isIsTruncated() {
        return this.isTruncated;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public Integer getMaxKeys() {
        return this.maxKeys;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }

    public List<Instance> getInstances() {
        return this.instances;
    }

    @Override
    public String toString() {
        return "ListInstanceResponse{"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "instances=" + instances + "\n"
                + "}";
    }

    public static class Instance {
        private String instanceId;
    
        private String instanceName;
    
        private String status;
    
        private String region;
    
        private String cfwId;
    
        private String cfwName;
    
        private String vpcId;
    
        private String vpcName;
    
        private String publicIp;
    
        private String role;
    
        private String localIfId;
    
        private String localIfName;
    
        private String peerRegion;
    
        private String peerVpcId;
    
        private String peerVpcName;
    
        private String memberId;
    
        private String memberName;
    
        private String memberAccountId;
    
        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }
    
        public String getInstanceId() {
            return this.instanceId;
        }
    
        public void setInstanceName(String instanceName) {
            this.instanceName = instanceName;
        }
    
        public String getInstanceName() {
            return this.instanceName;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getStatus() {
            return this.status;
        }
    
        public void setRegion(String region) {
            this.region = region;
        }
    
        public String getRegion() {
            return this.region;
        }
    
        public void setCfwId(String cfwId) {
            this.cfwId = cfwId;
        }
    
        public String getCfwId() {
            return this.cfwId;
        }
    
        public void setCfwName(String cfwName) {
            this.cfwName = cfwName;
        }
    
        public String getCfwName() {
            return this.cfwName;
        }
    
        public void setVpcId(String vpcId) {
            this.vpcId = vpcId;
        }
    
        public String getVpcId() {
            return this.vpcId;
        }
    
        public void setVpcName(String vpcName) {
            this.vpcName = vpcName;
        }
    
        public String getVpcName() {
            return this.vpcName;
        }
    
        public void setPublicIp(String publicIp) {
            this.publicIp = publicIp;
        }
    
        public String getPublicIp() {
            return this.publicIp;
        }
    
        public void setRole(String role) {
            this.role = role;
        }
    
        public String getRole() {
            return this.role;
        }
    
        public void setLocalIfId(String localIfId) {
            this.localIfId = localIfId;
        }
    
        public String getLocalIfId() {
            return this.localIfId;
        }
    
        public void setLocalIfName(String localIfName) {
            this.localIfName = localIfName;
        }
    
        public String getLocalIfName() {
            return this.localIfName;
        }
    
        public void setPeerRegion(String peerRegion) {
            this.peerRegion = peerRegion;
        }
    
        public String getPeerRegion() {
            return this.peerRegion;
        }
    
        public void setPeerVpcId(String peerVpcId) {
            this.peerVpcId = peerVpcId;
        }
    
        public String getPeerVpcId() {
            return this.peerVpcId;
        }
    
        public void setPeerVpcName(String peerVpcName) {
            this.peerVpcName = peerVpcName;
        }
    
        public String getPeerVpcName() {
            return this.peerVpcName;
        }
    
        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }
    
        public String getMemberId() {
            return this.memberId;
        }
    
        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }
    
        public String getMemberName() {
            return this.memberName;
        }
    
        public void setMemberAccountId(String memberAccountId) {
            this.memberAccountId = memberAccountId;
        }
    
        public String getMemberAccountId() {
            return this.memberAccountId;
        }
    
        @Override
        public String toString() {
            return "Instance{"
                    + "instanceId=" + instanceId + "\n"
                    + "instanceName=" + instanceName + "\n"
                    + "status=" + status + "\n"
                    + "region=" + region + "\n"
                    + "cfwId=" + cfwId + "\n"
                    + "cfwName=" + cfwName + "\n"
                    + "vpcId=" + vpcId + "\n"
                    + "vpcName=" + vpcName + "\n"
                    + "publicIp=" + publicIp + "\n"
                    + "role=" + role + "\n"
                    + "localIfId=" + localIfId + "\n"
                    + "localIfName=" + localIfName + "\n"
                    + "peerRegion=" + peerRegion + "\n"
                    + "peerVpcId=" + peerVpcId + "\n"
                    + "peerVpcName=" + peerVpcName + "\n"
                    + "memberId=" + memberId + "\n"
                    + "memberName=" + memberName + "\n"
                    + "memberAccountId=" + memberAccountId + "\n"
                    + "}";
        }
    }

}