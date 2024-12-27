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
import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCsnResponse extends BaseBceResponse {
    /**
     * 云智能网列表
     */
    private List<Csn> csns;

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
    private BigDecimal maxKeys;

    public void setCsns(List<Csn> csns) {
        this.csns = csns;
    }

    public List<Csn> getCsns() {
        return this.csns;
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
        return "ListCsnResponse{"
                + "csns=" + csns + "\n"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "}";
    }

    public static class Csn {
        private String csnId;
    
        private String name;
    
        private String description;
    
        private String status;
    
        private BigDecimal instanceNum;
    
        private BigDecimal csnBpNum;

        private List<Tag> tags;
    
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
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return this.description;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getStatus() {
            return this.status;
        }
    
        public void setInstanceNum(BigDecimal instanceNum) {
            this.instanceNum = instanceNum;
        }
    
        public BigDecimal getInstanceNum() {
            return this.instanceNum;
        }
    
        public void setCsnBpNum(BigDecimal csnBpNum) {
            this.csnBpNum = csnBpNum;
        }
    
        public BigDecimal getCsnBpNum() {
            return this.csnBpNum;
        }

        public List<Tag> getTags() {
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "Csn{" +
                    "csnId='" + csnId + '\'' +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", status='" + status + '\'' +
                    ", instanceNum=" + instanceNum +
                    ", csnBpNum=" + csnBpNum +
                    ", tags=" + tags +
                    '}';
        }
    }

}