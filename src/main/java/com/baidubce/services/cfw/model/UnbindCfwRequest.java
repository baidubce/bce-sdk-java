/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import java.util.List;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnbindCfwRequest extends BaseBceRequest {
    /**
     * 实例类型，取值[ eip | nat | etGateway | peerconn | csn | ipv6Gateway ]
     */
    private String instanceType;

    /**
     * 解绑实例信息
     */
    private List<CfwBind> instances;

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceType() {
        return this.instanceType;
    }

    public void setInstances(List<CfwBind> instances) {
        this.instances = instances;
    }

    public List<CfwBind> getInstances() {
        return this.instances;
    }

    @Override
    public String toString() {
        return "UnbindCfwRequest{"
                + "instanceType=" + instanceType + "\n"
                + "instances=" + instances + "\n"
                + "}";
    }

    public static class CfwBind {
        private String region;
    
        private String instanceId;
    
        private String role;
    
        private String memberId;
    
        public void setRegion(String region) {
            this.region = region;
        }
    
        public String getRegion() {
            return this.region;
        }
    
        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }
    
        public String getInstanceId() {
            return this.instanceId;
        }
    
        public void setRole(String role) {
            this.role = role;
        }
    
        public String getRole() {
            return this.role;
        }
    
        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }
    
        public String getMemberId() {
            return this.memberId;
        }
    
        @Override
        public String toString() {
            return "CfwBind{"
                    + "region=" + region + "\n"
                    + "instanceId=" + instanceId + "\n"
                    + "role=" + role + "\n"
                    + "memberId=" + memberId + "\n"
                    + "}";
        }
    }

}