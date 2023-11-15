/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import java.util.List;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCfwRuleRequest extends BaseBceRequest {
    /**
     * CFW规则列表
     */
    private List<CreateRule> cfwRules;

    public void setCfwRules(List<CreateRule> cfwRules) {
        this.cfwRules = cfwRules;
    }

    public List<CreateRule> getCfwRules() {
        return this.cfwRules;
    }

    @Override
    public String toString() {
        return "CreateCfwRuleRequest{"
                + "cfwRules=" + cfwRules + "\n"
                + "}";
    }

    public static class CreateRule {
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
    
        @Override
        public String toString() {
            return "CreateRule{"
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
                    + "}";
        }
    }

}