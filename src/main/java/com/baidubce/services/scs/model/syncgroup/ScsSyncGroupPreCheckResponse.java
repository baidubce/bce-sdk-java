package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsSyncGroupPreCheckResponse extends AbstractBceResponse {
    private List<CheckResult> checkResult;
    private List<ConnectionResult> connectionResults;

    public List<CheckResult> getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(List<CheckResult> checkResult) {
        this.checkResult = checkResult;
    }

    public List<ConnectionResult> getConnectionResults() {
        return connectionResults;
    }

    public void setConnectionResults(List<ConnectionResult> connectionResults) {
        this.connectionResults = connectionResults;
    }

    public static class CheckResult {
        private String memberId;
        private Boolean noData;
        private Boolean version;
        private Boolean engineVersion;
        private Boolean clusterStatus;
        private Boolean shardNum;
        private Boolean replicationNum;
        private Boolean flavor;
        private Boolean notJoined;
        private Boolean noSecurityGroup;
        private Boolean isHitX1;
        private Boolean isAppendOnlyOn;
        private Boolean samePasswd;
        private Boolean hasSameHashTagConf;
        private Boolean hasSetPwd;

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public Boolean getNoData() {
            return noData;
        }

        public void setNoData(Boolean noData) {
            this.noData = noData;
        }

        public Boolean getVersion() {
            return version;
        }

        public void setVersion(Boolean version) {
            this.version = version;
        }

        public Boolean getEngineVersion() {
            return engineVersion;
        }

        public void setEngineVersion(Boolean engineVersion) {
            this.engineVersion = engineVersion;
        }

        public Boolean getClusterStatus() {
            return clusterStatus;
        }

        public void setClusterStatus(Boolean clusterStatus) {
            this.clusterStatus = clusterStatus;
        }

        public Boolean getShardNum() {
            return shardNum;
        }

        public void setShardNum(Boolean shardNum) {
            this.shardNum = shardNum;
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

        public Boolean getNotJoined() {
            return notJoined;
        }

        public void setNotJoined(Boolean notJoined) {
            this.notJoined = notJoined;
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

        public Boolean getIsAppendOnlyOn() {
            return isAppendOnlyOn;
        }

        public void setIsAppendOnlyOn(Boolean isAppendOnlyOn) {
            this.isAppendOnlyOn = isAppendOnlyOn;
        }

        public Boolean getSamePasswd() {
            return samePasswd;
        }

        public void setSamePasswd(Boolean samePasswd) {
            this.samePasswd = samePasswd;
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
    }

    public static class ConnectionResult {
        private String sourceId;
        private String targetId;
        private Boolean connectable;

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public Boolean getConnectable() {
            return connectable;
        }

        public void setConnectable(Boolean connectable) {
            this.connectable = connectable;
        }
    }
}
