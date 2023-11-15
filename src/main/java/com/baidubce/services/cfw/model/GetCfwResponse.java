/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCfwResponse extends BaseBceResponse {
    /**
     * CFW的id
     */
    private String cfwId;

    /**
     * CFW的名称
     */
    private String name;

    /**
     * CFW的描述
     */
    private String description;

    /**
     * CFW的创建时间，标准UTC时间
     */
    private String createdTime;

    /**
     * CFW绑定实例的数量
     */
    private Integer bindInstanceNum;

    /**
     * CFW规则
     */
    private List<CfwRule> cfwRules;

    public void setCfwId(String cfwId) {
        this.cfwId = cfwId;
    }

    public String getCfwId() {
        return this.cfwId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedTime() {
        return this.createdTime;
    }

    public void setBindInstanceNum(Integer bindInstanceNum) {
        this.bindInstanceNum = bindInstanceNum;
    }

    public Integer getBindInstanceNum() {
        return this.bindInstanceNum;
    }

    public void setCfwRules(List<CfwRule> cfwRules) {
        this.cfwRules = cfwRules;
    }

    public List<CfwRule> getCfwRules() {
        return this.cfwRules;
    }

    @Override
    public String toString() {
        return "GetCfwResponse{"
                + "cfwId=" + cfwId + "\n"
                + "name=" + name + "\n"
                + "description=" + description + "\n"
                + "createdTime=" + createdTime + "\n"
                + "bindInstanceNum=" + bindInstanceNum + "\n"
                + "cfwRules=" + cfwRules + "\n"
                + "}";
    }

    public static class CfwRule {
        private Integer ipVersion;
    
        private Integer priority;
    
        private String protocol;
    
        private String direction;
    
        private String sourceAddress;
    
        private String destAddress;
    
        private String sourcePort;
    
        private String destPort;
    
        private String action;
    
        private String description;
    
        private String cfwId;
    
        private String cfwRuleId;
    
        public void setIpVersion(Integer ipVersion) {
            this.ipVersion = ipVersion;
        }
    
        public Integer getIpVersion() {
            return this.ipVersion;
        }
    
        public void setPriority(Integer priority) {
            this.priority = priority;
        }
    
        public Integer getPriority() {
            return this.priority;
        }
    
        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }
    
        public String getProtocol() {
            return this.protocol;
        }
    
        public void setDirection(String direction) {
            this.direction = direction;
        }
    
        public String getDirection() {
            return this.direction;
        }
    
        public void setSourceAddress(String sourceAddress) {
            this.sourceAddress = sourceAddress;
        }
    
        public String getSourceAddress() {
            return this.sourceAddress;
        }
    
        public void setDestAddress(String destAddress) {
            this.destAddress = destAddress;
        }
    
        public String getDestAddress() {
            return this.destAddress;
        }
    
        public void setSourcePort(String sourcePort) {
            this.sourcePort = sourcePort;
        }
    
        public String getSourcePort() {
            return this.sourcePort;
        }
    
        public void setDestPort(String destPort) {
            this.destPort = destPort;
        }
    
        public String getDestPort() {
            return this.destPort;
        }
    
        public void setAction(String action) {
            this.action = action;
        }
    
        public String getAction() {
            return this.action;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return this.description;
        }
    
        public void setCfwId(String cfwId) {
            this.cfwId = cfwId;
        }
    
        public String getCfwId() {
            return this.cfwId;
        }
    
        public void setCfwRuleId(String cfwRuleId) {
            this.cfwRuleId = cfwRuleId;
        }
    
        public String getCfwRuleId() {
            return this.cfwRuleId;
        }
    
        @Override
        public String toString() {
            return "CfwRule{"
                    + "ipVersion=" + ipVersion + "\n"
                    + "priority=" + priority + "\n"
                    + "protocol=" + protocol + "\n"
                    + "direction=" + direction + "\n"
                    + "sourceAddress=" + sourceAddress + "\n"
                    + "destAddress=" + destAddress + "\n"
                    + "sourcePort=" + sourcePort + "\n"
                    + "destPort=" + destPort + "\n"
                    + "action=" + action + "\n"
                    + "description=" + description + "\n"
                    + "cfwId=" + cfwId + "\n"
                    + "cfwRuleId=" + cfwRuleId + "\n"
                    + "}";
        }
    }

}