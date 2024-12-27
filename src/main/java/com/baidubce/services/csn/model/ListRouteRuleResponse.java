/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.csn.model;

import java.math.BigDecimal;
import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListRouteRuleResponse extends BaseBceResponse {
    /**
     * 路由条目列表
     */
    private List<CsnRtRule> csnRtRules;

    /**
     * 标记查询的起始位置，若结果列表为空，此项不存在
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
    private BigDecimal maxKeys;

    public void setCsnRtRules(List<CsnRtRule> csnRtRules) {
        this.csnRtRules = csnRtRules;
    }

    public List<CsnRtRule> getCsnRtRules() {
        return this.csnRtRules;
    }

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

    public void setMaxKeys(BigDecimal maxKeys) {
        this.maxKeys = maxKeys;
    }

    public BigDecimal getMaxKeys() {
        return this.maxKeys;
    }

    @Override
    public String toString() {
        return "ListRouteRuleResponse{"
                + "csnRtRules=" + csnRtRules + "\n"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "}";
    }

    public static class CsnRtRule {
        private String ruleId;
    
        private String routeType;
    
        private String csnId;
    
        private String csnRtId;
    
        private String description;
    
        private String fromAttachId;
    
        private String status;
    
        private String sourceAddress;
    
        private String destAddress;
    
        private String nextHopId;
    
        private String nextHopName;
    
        private String nextHopRegion;
    
        private String nextHopType;
    
        private String asPath;
    
        private String community;
    
        private Boolean blackHole;
    
        public void setRuleId(String ruleId) {
            this.ruleId = ruleId;
        }
    
        public String getRuleId() {
            return this.ruleId;
        }
    
        public void setRouteType(String routeType) {
            this.routeType = routeType;
        }
    
        public String getRouteType() {
            return this.routeType;
        }
    
        public void setCsnId(String csnId) {
            this.csnId = csnId;
        }
    
        public String getCsnId() {
            return this.csnId;
        }
    
        public void setCsnRtId(String csnRtId) {
            this.csnRtId = csnRtId;
        }
    
        public String getCsnRtId() {
            return this.csnRtId;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return this.description;
        }
    
        public void setFromAttachId(String fromAttachId) {
            this.fromAttachId = fromAttachId;
        }
    
        public String getFromAttachId() {
            return this.fromAttachId;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getStatus() {
            return this.status;
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
    
        public void setNextHopId(String nextHopId) {
            this.nextHopId = nextHopId;
        }
    
        public String getNextHopId() {
            return this.nextHopId;
        }
    
        public void setNextHopName(String nextHopName) {
            this.nextHopName = nextHopName;
        }
    
        public String getNextHopName() {
            return this.nextHopName;
        }
    
        public void setNextHopRegion(String nextHopRegion) {
            this.nextHopRegion = nextHopRegion;
        }
    
        public String getNextHopRegion() {
            return this.nextHopRegion;
        }
    
        public void setNextHopType(String nextHopType) {
            this.nextHopType = nextHopType;
        }
    
        public String getNextHopType() {
            return this.nextHopType;
        }
    
        public void setAsPath(String asPath) {
            this.asPath = asPath;
        }
    
        public String getAsPath() {
            return this.asPath;
        }
    
        public void setCommunity(String community) {
            this.community = community;
        }
    
        public String getCommunity() {
            return this.community;
        }
    
        public void setBlackHole(Boolean blackHole) {
            this.blackHole = blackHole;
        }
    
        public Boolean isBlackHole() {
            return this.blackHole;
        }
    
        @Override
        public String toString() {
            return "CsnRtRule{"
                    + "ruleId=" + ruleId + "\n"
                    + "routeType=" + routeType + "\n"
                    + "csnId=" + csnId + "\n"
                    + "csnRtId=" + csnRtId + "\n"
                    + "description=" + description + "\n"
                    + "fromAttachId=" + fromAttachId + "\n"
                    + "status=" + status + "\n"
                    + "sourceAddress=" + sourceAddress + "\n"
                    + "destAddress=" + destAddress + "\n"
                    + "nextHopId=" + nextHopId + "\n"
                    + "nextHopName=" + nextHopName + "\n"
                    + "nextHopRegion=" + nextHopRegion + "\n"
                    + "nextHopType=" + nextHopType + "\n"
                    + "asPath=" + asPath + "\n"
                    + "community=" + community + "\n"
                    + "blackHole=" + blackHole + "\n"
                    + "}";
        }
    }

}