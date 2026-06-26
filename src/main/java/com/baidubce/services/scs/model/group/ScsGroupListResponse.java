package com.baidubce.services.scs.model.group;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsGroupListResponse extends AbstractBceResponse {
    private Integer totalCount;
    private Integer pageNo;
    private Integer pageSize;
    private List<Group> result;

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

    public List<Group> getResult() {
        return result;
    }

    public void setResult(List<Group> result) {
        this.result = result;
    }

    public static class Group {
        private String groupId;
        private String groupName;
        private String groupStatus;
        private Integer clusterNum;
        private String groupCreateTime;
        private Integer forbidWrite;
        private String groupType;
        private String bnsGroup;
        private String leaderName;
        private String leaderShowId;
        private String leaderRegion;

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

        public String getLeaderName() {
            return leaderName;
        }

        public void setLeaderName(String leaderName) {
            this.leaderName = leaderName;
        }

        public String getLeaderShowId() {
            return leaderShowId;
        }

        public void setLeaderShowId(String leaderShowId) {
            this.leaderShowId = leaderShowId;
        }

        public String getLeaderRegion() {
            return leaderRegion;
        }

        public void setLeaderRegion(String leaderRegion) {
            this.leaderRegion = leaderRegion;
        }
    }
}
