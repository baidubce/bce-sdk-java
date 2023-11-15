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
public class DescribeFsResponse extends BaseBceResponse {
    /**
     * 包含查询结果的列表
     */
    private List<FileSystemModel> fileSystemList;

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
     * 每页结果包含的数量
     */
    private Integer maxKeys;

    public void setFileSystemList(List<FileSystemModel> fileSystemList) {
        this.fileSystemList = fileSystemList;
    }

    public List<FileSystemModel> getFileSystemList() {
        return this.fileSystemList;
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

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public Integer getMaxKeys() {
        return this.maxKeys;
    }

    @Override
    public String toString() {
        return "DescribeFsResponse{"
                + "fileSystemList=" + fileSystemList + "\n"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "}";
    }

    public static class FileSystemModel {
        private String fsId;

        private String fsName;

        private String type;

        private String protocol;

        private String vpcId;

        private List<MountTargetModel> mountTargetList;

        private String status;

        public void setFsId(String fsId) {
            this.fsId = fsId;
        }

        public String getFsId() {
            return this.fsId;
        }

        public void setFsName(String fsName) {
            this.fsName = fsName;
        }

        public String getFsName() {
            return this.fsName;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getProtocol() {
            return this.protocol;
        }

        public void setVpcId(String vpcId) {
            this.vpcId = vpcId;
        }

        public String getVpcId() {
            return this.vpcId;
        }

        public void setMountTargetList(List<MountTargetModel> mountTargetList) {
            this.mountTargetList = mountTargetList;
        }

        public List<MountTargetModel> getMountTargetList() {
            return this.mountTargetList;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }

        @Override
        public String toString() {
            return "FileSystemModel{"
                    + "fsId=" + fsId + "\n"
                    + "fsName=" + fsName + "\n"
                    + "type=" + type + "\n"
                    + "protocol=" + protocol + "\n"
                    + "vpcId=" + vpcId + "\n"
                    + "mountTargetList=" + mountTargetList + "\n"
                    + "status=" + status + "\n"
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

}