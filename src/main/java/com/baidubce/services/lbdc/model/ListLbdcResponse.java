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
package com.baidubce.services.lbdc.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListLbdcResponse extends BaseBceResponse {
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
     * 包含查询结果的LBDC列表
     */
    private List<ClusterModel> clusterList;

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

    public void setClusterList(List<ClusterModel> clusterList) {
        this.clusterList = clusterList;
    }

    public List<ClusterModel> getClusterList() {
        return this.clusterList;
    }

    @Override
    public String toString() {
        return "ListLbdcResponse{"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "clusterList=" + clusterList + "\n"
                + "}";
    }

    public static class ClusterModel {
        private String id;
    
        private String name;
    
        private String type;
    
        private String status;
    
        private Integer ccuCount;
    
        private String createTime;
    
        private String expireTime;
    
        private String desc;
    
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
    
        public void setType(String type) {
            this.type = type;
        }
    
        public String getType() {
            return this.type;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getStatus() {
            return this.status;
        }
    
        public void setCcuCount(Integer ccuCount) {
            this.ccuCount = ccuCount;
        }
    
        public Integer getCcuCount() {
            return this.ccuCount;
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
    
        public void setDesc(String desc) {
            this.desc = desc;
        }
    
        public String getDesc() {
            return this.desc;
        }
    
        @Override
        public String toString() {
            return "ClusterModel{"
                    + "id=" + id + "\n"
                    + "name=" + name + "\n"
                    + "type=" + type + "\n"
                    + "status=" + status + "\n"
                    + "ccuCount=" + ccuCount + "\n"
                    + "createTime=" + createTime + "\n"
                    + "expireTime=" + expireTime + "\n"
                    + "desc=" + desc + "\n"
                    + "}";
        }
    }

}