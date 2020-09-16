/**
 * Copyright 2020 Baidu, Inc.
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
package com.baidubce.services.bes.model;

import com.baidubce.model.AbstractBceResponse;

import java.io.Serializable;
import java.util.List;

/**
 *  @Description:  View the response for the cluster list
 */
public class BesListClusterResponse extends AbstractBceResponse implements Serializable {

    private Boolean success;

    private int status;

    private ListPageResponse page;

    public ListPageResponse getPage() {
        return page;
    }

    public void setPage(ListPageResponse page) {
        this.page = page;
    }

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

    public static class ListPageResponse {

        private int pageNo;

        private int pageSize;

        private int totalCount;

        private List<ClusterSummaryInfo> result;

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

        public List<ClusterSummaryInfo> getResult() {
            return result;
        }

        public void setResult(List<ClusterSummaryInfo> result) {
            this.result = result;
        }

        public static class ClusterSummaryInfo {
            private String clusterId;
            private String clusterName;
            private String createTime;
            private String adminUsername;
            private String desireStatus;
            private String actualStatus;
            private String runningTime;
            private String connectUrl;
            private BesClusterBillingResponse billing;

            public BesClusterBillingResponse getBilling() {
                return billing;
            }

            public void setBilling(BesClusterBillingResponse billing) {
                this.billing = billing;
            }

            public String getClusterId() {
                return clusterId;
            }

            public void setClusterId(String clusterId) {
                this.clusterId = clusterId;
            }

            public String getClusterName() {
                return clusterName;
            }

            public void setClusterName(String clusterName) {
                this.clusterName = clusterName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getAdminUsername() {
                return adminUsername;
            }

            public void setAdminUsername(String adminUsername) {
                this.adminUsername = adminUsername;
            }

            public String getDesireStatus() {
                return desireStatus;
            }

            public void setDesireStatus(String desireStatus) {
                this.desireStatus = desireStatus;
            }

            public String getActualStatus() {
                return actualStatus;
            }

            public void setActualStatus(String actualStatus) {
                this.actualStatus = actualStatus;
            }

            public String getRunningTime() {
                return runningTime;
            }

            public void setRunningTime(String runningTime) {
                this.runningTime = runningTime;
            }

            public String getConnectUrl() {
                return connectUrl;
            }

            public void setConnectUrl(String connectUrl) {
                this.connectUrl = connectUrl;
            }

        }
    }
}
