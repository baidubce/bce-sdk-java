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
public class ListRecordResponse extends BaseBceResponse {
    /**
     * 标记查询的起始位置。
     */
    private String marker;

    /**
     * true表示后面还有数据，false表示已经是最后一页。
     */
    private Boolean isTruncated;

    /**
     * 获取下一页所需要传递的marker值。当isTruncated为false时，该域不出现。
     */
    private String nextMarker;

    /**
     * 每页包含的最大数量。
     */
    private Integer maxKeys;

    /**
     * 包含查询结果的解析记录列表。
     */
    private List<Record> records;

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

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Record> getRecords() {
        return this.records;
    }

    @Override
    public String toString() {
        return "ListRecordResponse{"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "records=" + records + "\n"
                + "}";
    }

    public static class Record {
        private String id;
    
        private String rr;
    
        private String status;
    
        private String type;
    
        private String value;
    
        private Integer ttl;
    
        private String line;
    
        private String description;
    
        private Integer priority;
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getId() {
            return this.id;
        }
    
        public void setRr(String rr) {
            this.rr = rr;
        }
    
        public String getRr() {
            return this.rr;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getStatus() {
            return this.status;
        }
    
        public void setType(String type) {
            this.type = type;
        }
    
        public String getType() {
            return this.type;
        }
    
        public void setValue(String value) {
            this.value = value;
        }
    
        public String getValue() {
            return this.value;
        }
    
        public void setTtl(Integer ttl) {
            this.ttl = ttl;
        }
    
        public Integer getTtl() {
            return this.ttl;
        }
    
        public void setLine(String line) {
            this.line = line;
        }
    
        public String getLine() {
            return this.line;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return this.description;
        }
    
        public void setPriority(Integer priority) {
            this.priority = priority;
        }
    
        public Integer getPriority() {
            return this.priority;
        }
    
        @Override
        public String toString() {
            return "Record{"
                    + "id=" + id + "\n"
                    + "rr=" + rr + "\n"
                    + "status=" + status + "\n"
                    + "type=" + type + "\n"
                    + "value=" + value + "\n"
                    + "ttl=" + ttl + "\n"
                    + "line=" + line + "\n"
                    + "description=" + description + "\n"
                    + "priority=" + priority + "\n"
                    + "}";
        }
    }

}