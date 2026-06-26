package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsSyncGroupListResponse extends AbstractBceResponse {
    private Integer totalCount;
    private Integer pageNo;
    private Integer pageSize;
    private List<ListItem> result;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<ListItem> getResult() {
        return result;
    }

    public void setResult(List<ListItem> result) {
        this.result = result;
    }

    public static class ListItem {
        private String syncGroupShowId;
        private String syncGroupName;
        private String status;
        private Integer clusterNum;
        private String nodeType;
        private String netConn;
        private String confilctResolution;
        private String syncGroupCreateTime;
        private Boolean sameSpec;
        private Boolean sameShardNum;
        private List<ClusterItem> cluster;

        public String getSyncGroupShowId() {
            return syncGroupShowId;
        }

        public void setSyncGroupShowId(String syncGroupShowId) {
            this.syncGroupShowId = syncGroupShowId;
        }

        public String getSyncGroupName() {
            return syncGroupName;
        }

        public void setSyncGroupName(String syncGroupName) {
            this.syncGroupName = syncGroupName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getClusterNum() {
            return clusterNum;
        }

        public void setClusterNum(Integer clusterNum) {
            this.clusterNum = clusterNum;
        }

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
        }

        public String getNetConn() {
            return netConn;
        }

        public void setNetConn(String netConn) {
            this.netConn = netConn;
        }

        public String getConfilctResolution() {
            return confilctResolution;
        }

        public void setConfilctResolution(String confilctResolution) {
            this.confilctResolution = confilctResolution;
        }

        public String getSyncGroupCreateTime() {
            return syncGroupCreateTime;
        }

        public void setSyncGroupCreateTime(String syncGroupCreateTime) {
            this.syncGroupCreateTime = syncGroupCreateTime;
        }

        public Boolean getSameSpec() {
            return sameSpec;
        }

        public void setSameSpec(Boolean sameSpec) {
            this.sameSpec = sameSpec;
        }

        public Boolean getSameShardNum() {
            return sameShardNum;
        }

        public void setSameShardNum(Boolean sameShardNum) {
            this.sameShardNum = sameShardNum;
        }

        public List<ClusterItem> getCluster() {
            return cluster;
        }

        public void setCluster(List<ClusterItem> cluster) {
            this.cluster = cluster;
        }
    }

    public static class ClusterItem {
        private String clusterShowId;
        private String region;

        public String getClusterShowId() {
            return clusterShowId;
        }

        public void setClusterShowId(String clusterShowId) {
            this.clusterShowId = clusterShowId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }
}
