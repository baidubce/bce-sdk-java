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
public class ListTgwResponse extends BaseBceResponse {
    /**
     * TGW列表
     */
    private List<Tgw> tgws;

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

    public void setTgws(List<Tgw> tgws) {
        this.tgws = tgws;
    }

    public List<Tgw> getTgws() {
        return this.tgws;
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
        return "ListTgwResponse{"
                + "tgws=" + tgws + "\n"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "}";
    }

    public static class Tgw {
        private String tgwId;
    
        private String csnId;
    
        private String name;
    
        private String region;
    
        private String description;
    
        public void setTgwId(String tgwId) {
            this.tgwId = tgwId;
        }
    
        public String getTgwId() {
            return this.tgwId;
        }
    
        public void setCsnId(String csnId) {
            this.csnId = csnId;
        }
    
        public String getCsnId() {
            return this.csnId;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getName() {
            return this.name;
        }
    
        public void setRegion(String region) {
            this.region = region;
        }
    
        public String getRegion() {
            return this.region;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return this.description;
        }
    
        @Override
        public String toString() {
            return "Tgw{"
                    + "tgwId=" + tgwId + "\n"
                    + "csnId=" + csnId + "\n"
                    + "name=" + name + "\n"
                    + "region=" + region + "\n"
                    + "description=" + description + "\n"
                    + "}";
        }
    }
}