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
public class ListLineGroupResponse extends BaseBceResponse {
    /**
     * 标记查询的起始位置。
     */
    private String marker;

    /**
     * true表示后面还有数据，false表示已经是最后一页。
     */
    private Boolean isTruncated;

    /**
     * 获取下一页所需要传递的marker值，当isTruncated为false时，该域不出现。
     */
    private String nextMarker;

    /**
     * 每页包含的最大数量。
     */
    private Integer maxKeys;

    /**
     * 包含查询结果的线路组列表。
     */
    private List<Line> lineList;

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

    public void setLineList(List<Line> lineList) {
        this.lineList = lineList;
    }

    public List<Line> getLineList() {
        return this.lineList;
    }

    @Override
    public String toString() {
        return "ListLineGroupResponse{"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "lineList=" + lineList + "\n"
                + "}";
    }

    public static class Line {
        private String id;
    
        private String name;
    
        private List<String> lines;
    
        private Integer relatedZoneCount;
    
        private Integer relatedRecordCount;
    
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
    
        public void setLines(List<String> lines) {
            this.lines = lines;
        }
    
        public List<String> getLines() {
            return this.lines;
        }
    
        public void setRelatedZoneCount(Integer relatedZoneCount) {
            this.relatedZoneCount = relatedZoneCount;
        }
    
        public Integer getRelatedZoneCount() {
            return this.relatedZoneCount;
        }
    
        public void setRelatedRecordCount(Integer relatedRecordCount) {
            this.relatedRecordCount = relatedRecordCount;
        }
    
        public Integer getRelatedRecordCount() {
            return this.relatedRecordCount;
        }
    
        @Override
        public String toString() {
            return "Line{"
                    + "id=" + id + "\n"
                    + "name=" + name + "\n"
                    + "lines=" + lines + "\n"
                    + "relatedZoneCount=" + relatedZoneCount + "\n"
                    + "relatedRecordCount=" + relatedRecordCount + "\n"
                    + "}";
        }
    }

}