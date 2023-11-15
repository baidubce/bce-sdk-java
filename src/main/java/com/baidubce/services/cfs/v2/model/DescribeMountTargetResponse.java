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
package com.baidubce.services.cfs.v2.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DescribeMountTargetResponse extends BaseBceResponse {
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
    private Integer maxKeys;

    /**
     * mountTargetList
     */
    private List<MountTargetModel> mountTargetList;

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

    public void setMountTargetList(List<MountTargetModel> mountTargetList) {
        this.mountTargetList = mountTargetList;
    }

    public List<MountTargetModel> getMountTargetList() {
        return this.mountTargetList;
    }

    @Override
    public String toString() {
        return "DescribeMountTargetResponse{"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "mountTargetList=" + mountTargetList + "\n"
                + "}";
    }

    public static class MountTargetModel {
        private String subnetId;

        private String domain;

        private String mountId;

        public void setSubnetId(String subnetId) {
            this.subnetId = subnetId;
        }

        public String getSubnetId() {
            return this.subnetId;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getDomain() {
            return this.domain;
        }

        public void setMountId(String mountId) {
            this.mountId = mountId;
        }

        public String getMountId() {
            return this.mountId;
        }

        @Override
        public String toString() {
            return "MountTargetModel{"
                    + "subnetId=" + subnetId + "\n"
                    + "domain=" + domain + "\n"
                    + "mountId=" + mountId + "\n"
                    + "}";
        }
    }

}