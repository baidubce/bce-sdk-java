/**
 * Copyright 2020 Baidu, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bes.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BesGetRenewListResponse extends AbstractBceResponse implements Serializable {
    private Boolean success;
    private int status;
    private Page page;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public static class Page {
        private String orderBy = "";
        private String order = "";
        private int pageNo;
        private int pageSize;
        private int totalCount;
        private List<BesRenewListResponse> result;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<BesRenewListResponse> getResult() {
            return result;
        }

        public void setResult(List<BesRenewListResponse> result) {
            this.result = result;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public static class BesRenewListResponse {
            private String clusterId;
            private String clusterName;
            private String region;
            @JsonFormat(
                    shape = JsonFormat.Shape.STRING,
                    pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
                    timezone = "UTC"
            )
            private Date expiredTime;
            private String clusterStatus;

            public String getClusterId() {
                return this.clusterId;
            }

            public void setClusterId(String clusterId) {
                this.clusterId = clusterId;
            }

            public String getClusterName() {
                return this.clusterName;
            }

            public void setClusterName(String clusterName) {
                this.clusterName = clusterName;
            }

            public String getRegion() {
                return this.region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public Date getExpiredTime() {
                return this.expiredTime;
            }

            public void setExpiredTime(Date expiredTime) {
                this.expiredTime = expiredTime;
            }

            public String getClusterStatus() {
                return this.clusterStatus;
            }

            public void setClusterStatus(String clusterStatus) {
                this.clusterStatus = clusterStatus;
            }
        }
    }
}