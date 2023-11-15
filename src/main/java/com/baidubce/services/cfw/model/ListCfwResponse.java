/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCfwResponse extends BaseBceResponse {
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
     * CFW列表
     */
    private List<Cfw> cfws;

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

    public void setCfws(List<Cfw> cfws) {
        this.cfws = cfws;
    }

    public List<Cfw> getCfws() {
        return this.cfws;
    }

    @Override
    public String toString() {
        return "ListCfwResponse{"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "cfws=" + cfws + "\n"
                + "}";
    }

    public static class Cfw {
        private String cfwId;
    
        private String name;
    
        private String description;
    
        private String createdTime;
    
        private Integer bindInstanceNum;
    
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
            return "Cfw{"
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

}