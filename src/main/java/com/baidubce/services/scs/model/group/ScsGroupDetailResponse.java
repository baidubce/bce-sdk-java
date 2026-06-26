package com.baidubce.services.scs.model.group;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsGroupDetailResponse extends AbstractBceResponse {
    private String groupId;
    private String groupName;
    private String groupStatus;
    private Integer clusterNum;
    private String groupCreateTime;
    private Integer forbidWrite;
    private String groupType;
    private String bnsGroup;
    private Cluster leader;
    private List<Cluster> followers;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Integer getClusterNum() {
        return clusterNum;
    }

    public void setClusterNum(Integer clusterNum) {
        this.clusterNum = clusterNum;
    }

    public String getGroupCreateTime() {
        return groupCreateTime;
    }

    public void setGroupCreateTime(String groupCreateTime) {
        this.groupCreateTime = groupCreateTime;
    }

    public Integer getForbidWrite() {
        return forbidWrite;
    }

    public void setForbidWrite(Integer forbidWrite) {
        this.forbidWrite = forbidWrite;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getBnsGroup() {
        return bnsGroup;
    }

    public void setBnsGroup(String bnsGroup) {
        this.bnsGroup = bnsGroup;
    }

    public Cluster getLeader() {
        return leader;
    }

    public void setLeader(Cluster leader) {
        this.leader = leader;
    }

    public List<Cluster> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Cluster> followers) {
        this.followers = followers;
    }

    public static class Cluster {
        private String clusterName;
        private String clusterShowId;
        private String region;
        private String status;
        private Double totalCapacityInGB;
        private Double usedCapacityInGB;
        private Double usedCapacityInMB;
        private Integer shardNum;
        private Double flavor;
        private Integer qpsWrite;
        private Integer qpsRead;
        private Boolean staleReadable;
        private Integer forbidWrite;
        private String availabilityZone;
        private String expiredTime;
        private List<String> shardHashNames;

        public String getClusterName() {
            return clusterName;
        }

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Double getTotalCapacityInGB() {
            return totalCapacityInGB;
        }

        public void setTotalCapacityInGB(Double totalCapacityInGB) {
            this.totalCapacityInGB = totalCapacityInGB;
        }

        public Double getUsedCapacityInGB() {
            return usedCapacityInGB;
        }

        public void setUsedCapacityInGB(Double usedCapacityInGB) {
            this.usedCapacityInGB = usedCapacityInGB;
        }

        public Double getUsedCapacityInMB() {
            return usedCapacityInMB;
        }

        public void setUsedCapacityInMB(Double usedCapacityInMB) {
            this.usedCapacityInMB = usedCapacityInMB;
        }

        public Integer getShardNum() {
            return shardNum;
        }

        public void setShardNum(Integer shardNum) {
            this.shardNum = shardNum;
        }

        public Double getFlavor() {
            return flavor;
        }

        public void setFlavor(Double flavor) {
            this.flavor = flavor;
        }

        public Integer getQpsWrite() {
            return qpsWrite;
        }

        public void setQpsWrite(Integer qpsWrite) {
            this.qpsWrite = qpsWrite;
        }

        public Integer getQpsRead() {
            return qpsRead;
        }

        public void setQpsRead(Integer qpsRead) {
            this.qpsRead = qpsRead;
        }

        public Boolean getStaleReadable() {
            return staleReadable;
        }

        public void setStaleReadable(Boolean staleReadable) {
            this.staleReadable = staleReadable;
        }

        public Integer getForbidWrite() {
            return forbidWrite;
        }

        public void setForbidWrite(Integer forbidWrite) {
            this.forbidWrite = forbidWrite;
        }

        public String getAvailabilityZone() {
            return availabilityZone;
        }

        public void setAvailabilityZone(String availabilityZone) {
            this.availabilityZone = availabilityZone;
        }

        public String getExpiredTime() {
            return expiredTime;
        }

        public void setExpiredTime(String expiredTime) {
            this.expiredTime = expiredTime;
        }

        public List<String> getShardHashNames() {
            return shardHashNames;
        }

        public void setShardHashNames(List<String> shardHashNames) {
            this.shardHashNames = shardHashNames;
        }
    }
}
