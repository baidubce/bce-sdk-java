package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsSyncGroupDetailResponse extends AbstractBceResponse {
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

    public static class ClusterItem {
        private Integer clusterId;
        private String clusterShowId;
        private String clusterName;
        private String region;
        private String clusterStatus;
        private String clusterEngine;
        private String createTime;
        private Float totalCapacityInGb;
        private Float usedCapacityInGb;
        private String expiredTime;
        private List<String> shardList;
        private List<SyncFlowItem> syncFlow;

        public Integer getClusterId() {
            return clusterId;
        }

        public void setClusterId(Integer clusterId) {
            this.clusterId = clusterId;
        }

        public String getClusterShowId() {
            return clusterShowId;
        }

        public void setClusterShowId(String clusterShowId) {
            this.clusterShowId = clusterShowId;
        }

        public String getClusterName() {
            return clusterName;
        }

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getClusterStatus() {
            return clusterStatus;
        }

        public void setClusterStatus(String clusterStatus) {
            this.clusterStatus = clusterStatus;
        }

        public String getClusterEngine() {
            return clusterEngine;
        }

        public void setClusterEngine(String clusterEngine) {
            this.clusterEngine = clusterEngine;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Float getTotalCapacityInGb() {
            return totalCapacityInGb;
        }

        public void setTotalCapacityInGb(Float totalCapacityInGb) {
            this.totalCapacityInGb = totalCapacityInGb;
        }

        public Float getUsedCapacityInGb() {
            return usedCapacityInGb;
        }

        public void setUsedCapacityInGb(Float usedCapacityInGb) {
            this.usedCapacityInGb = usedCapacityInGb;
        }

        public String getExpiredTime() {
            return expiredTime;
        }

        public void setExpiredTime(String expiredTime) {
            this.expiredTime = expiredTime;
        }

        public List<String> getShardList() {
            return shardList;
        }

        public void setShardList(List<String> shardList) {
            this.shardList = shardList;
        }

        public List<SyncFlowItem> getSyncFlow() {
            return syncFlow;
        }

        public void setSyncFlow(List<SyncFlowItem> syncFlow) {
            this.syncFlow = syncFlow;
        }
    }

    public static class SyncFlowItem {
        private String targetBLBIp;
        private Integer targetBLBPort;
        private String targetClusterShowId;

        public String getTargetBLBIp() {
            return targetBLBIp;
        }

        public void setTargetBLBIp(String targetBLBIp) {
            this.targetBLBIp = targetBLBIp;
        }

        public Integer getTargetBLBPort() {
            return targetBLBPort;
        }

        public void setTargetBLBPort(Integer targetBLBPort) {
            this.targetBLBPort = targetBLBPort;
        }

        public String getTargetClusterShowId() {
            return targetClusterShowId;
        }

        public void setTargetClusterShowId(String targetClusterShowId) {
            this.targetClusterShowId = targetClusterShowId;
        }
    }
}
