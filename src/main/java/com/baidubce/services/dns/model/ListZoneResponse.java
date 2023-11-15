/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.dns.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListZoneResponse extends BaseBceResponse {
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
     * 包含查询结果的域名列表。
     */
    private List<Zone> zones;

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

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public List<Zone> getZones() {
        return this.zones;
    }

    @Override
    public String toString() {
        return "ListZoneResponse{"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "zones=" + zones + "\n"
                + "}";
    }

    public static class Zone {
        private String id;
    
        private String name;
    
        private String status;
    
        private String productVersion;
    
        private String createTime;
    
        private String expireTime;
    
        private List<TagModel> tags;
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getId() {
            return this.id;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getName() {
            return this.name;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getStatus() {
            return this.status;
        }
    
        public void setProductVersion(String productVersion) {
            this.productVersion = productVersion;
        }
    
        public String getProductVersion() {
            return this.productVersion;
        }
    
        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    
        public String getCreateTime() {
            return this.createTime;
        }
    
        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }
    
        public String getExpireTime() {
            return this.expireTime;
        }
    
        public void setTags(List<TagModel> tags) {
            this.tags = tags;
        }
    
        public List<TagModel> getTags() {
            return this.tags;
        }
    
        @Override
        public String toString() {
            return "Zone{"
                    + "id=" + id + "\n"
                    + "name=" + name + "\n"
                    + "status=" + status + "\n"
                    + "productVersion=" + productVersion + "\n"
                    + "createTime=" + createTime + "\n"
                    + "expireTime=" + expireTime + "\n"
                    + "tags=" + tags + "\n"
                    + "}";
        }
    
        public static class TagModel {
            private String tagKey;
        
            private String tagValue;
        
            public void setTagKey(String tagKey) {
                this.tagKey = tagKey;
            }
        
            public String getTagKey() {
                return this.tagKey;
            }
        
            public void setTagValue(String tagValue) {
                this.tagValue = tagValue;
            }
        
            public String getTagValue() {
                return this.tagValue;
            }
        
            @Override
            public String toString() {
                return "TagModel{"
                        + "tagKey=" + tagKey + "\n"
                        + "tagValue=" + tagValue + "\n"
                        + "}";
            }
        }    
    }

}