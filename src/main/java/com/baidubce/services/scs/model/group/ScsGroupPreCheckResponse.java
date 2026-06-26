package com.baidubce.services.scs.model.group;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsGroupPreCheckResponse extends AbstractBceResponse {
    private LeaderResult leaderResult;
    private List<FollowerResult> followerResult;
    private List<ConnectionResult> connectionResults;

    public LeaderResult getLeaderResult() {
        return leaderResult;
    }

    public void setLeaderResult(LeaderResult leaderResult) {
        this.leaderResult = leaderResult;
    }

    public List<FollowerResult> getFollowerResult() {
        return followerResult;
    }

    public void setFollowerResult(List<FollowerResult> followerResult) {
        this.followerResult = followerResult;
    }

    public List<ConnectionResult> getConnectionResults() {
        return connectionResults;
    }

    public void setConnectionResults(List<ConnectionResult> connectionResults) {
        this.connectionResults = connectionResults;
    }

    public static class LeaderResult {
        private Boolean version;
        private Boolean clusterStatus;
        private Boolean replicationNum;
        private Boolean flavor;
        private Boolean joined;
        private Boolean noPasswd;
        private Boolean noSecurityGroup;
        private Boolean isHitX1;
        private Boolean noTde;
        private Boolean hasSameHashTagConf;
        private Boolean hasSetPwd;
        private Boolean isNotCrossAzNearest;

        public Boolean getVersion() {
            return version;
        }

        public void setVersion(Boolean version) {
            this.version = version;
        }

        public Boolean getClusterStatus() {
            return clusterStatus;
        }

        public void setClusterStatus(Boolean clusterStatus) {
            this.clusterStatus = clusterStatus;
        }

        public Boolean getReplicationNum() {
            return replicationNum;
        }

        public void setReplicationNum(Boolean replicationNum) {
            this.replicationNum = replicationNum;
        }

        public Boolean getFlavor() {
            return flavor;
        }

        public void setFlavor(Boolean flavor) {
            this.flavor = flavor;
        }

        public Boolean getJoined() {
            return joined;
        }

        public void setJoined(Boolean joined) {
            this.joined = joined;
        }

        public Boolean getNoPasswd() {
            return noPasswd;
        }

        public void setNoPasswd(Boolean noPasswd) {
            this.noPasswd = noPasswd;
        }

        public Boolean getNoSecurityGroup() {
            return noSecurityGroup;
        }

        public void setNoSecurityGroup(Boolean noSecurityGroup) {
            this.noSecurityGroup = noSecurityGroup;
        }

        public Boolean getIsHitX1() {
            return isHitX1;
        }

        public void setIsHitX1(Boolean isHitX1) {
            this.isHitX1 = isHitX1;
        }

        public Boolean getNoTde() {
            return noTde;
        }

        public void setNoTde(Boolean noTde) {
            this.noTde = noTde;
        }

        public Boolean getHasSameHashTagConf() {
            return hasSameHashTagConf;
        }

        public void setHasSameHashTagConf(Boolean hasSameHashTagConf) {
            this.hasSameHashTagConf = hasSameHashTagConf;
        }

        public Boolean getHasSetPwd() {
            return hasSetPwd;
        }

        public void setHasSetPwd(Boolean hasSetPwd) {
            this.hasSetPwd = hasSetPwd;
        }

        public Boolean getIsNotCrossAzNearest() {
            return isNotCrossAzNearest;
        }

        public void setIsNotCrossAzNearest(Boolean isNotCrossAzNearest) {
            this.isNotCrossAzNearest = isNotCrossAzNearest;
        }
    }

    public static class FollowerResult extends LeaderResult {
        private String followerId;
        private Boolean noData;
        private Boolean engineVersion;
        private Boolean shardNum;
        private Boolean samePasswd;

        public String getFollowerId() {
            return followerId;
        }

        public void setFollowerId(String followerId) {
            this.followerId = followerId;
        }

        public Boolean getNoData() {
            return noData;
        }

        public void setNoData(Boolean noData) {
            this.noData = noData;
        }

        public Boolean getEngineVersion() {
            return engineVersion;
        }

        public void setEngineVersion(Boolean engineVersion) {
            this.engineVersion = engineVersion;
        }

        public Boolean getShardNum() {
            return shardNum;
        }

        public void setShardNum(Boolean shardNum) {
            this.shardNum = shardNum;
        }

        public Boolean getSamePasswd() {
            return samePasswd;
        }

        public void setSamePasswd(Boolean samePasswd) {
            this.samePasswd = samePasswd;
        }
    }

    public static class ConnectionResult {
        private String sourceId;
        private String sourceRole;
        private String targetId;
        private String targetRole;
        private Boolean connectable;

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getSourceRole() {
            return sourceRole;
        }

        public void setSourceRole(String sourceRole) {
            this.sourceRole = sourceRole;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getTargetRole() {
            return targetRole;
        }

        public void setTargetRole(String targetRole) {
            this.targetRole = targetRole;
        }

        public Boolean getConnectable() {
            return connectable;
        }

        public void setConnectable(Boolean connectable) {
            this.connectable = connectable;
        }
    }
}
