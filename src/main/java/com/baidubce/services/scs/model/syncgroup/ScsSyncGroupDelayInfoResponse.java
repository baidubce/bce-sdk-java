package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsSyncGroupDelayInfoResponse extends AbstractBceResponse {
    private List<DelayInfo> delayInfo;

    public List<DelayInfo> getDelayInfo() {
        return delayInfo;
    }

    public void setDelayInfo(List<DelayInfo> delayInfo) {
        this.delayInfo = delayInfo;
    }

    public static class DelayInfo {
        private String sourceCluster;
        private String destCluster;
        private Integer delayResult;
        private Integer timeResult;

        public String getSourceCluster() {
            return sourceCluster;
        }

        public void setSourceCluster(String sourceCluster) {
            this.sourceCluster = sourceCluster;
        }

        public String getDestCluster() {
            return destCluster;
        }

        public void setDestCluster(String destCluster) {
            this.destCluster = destCluster;
        }

        public Integer getDelayResult() {
            return delayResult;
        }

        public void setDelayResult(Integer delayResult) {
            this.delayResult = delayResult;
        }

        public Integer getTimeResult() {
            return timeResult;
        }

        public void setTimeResult(Integer timeResult) {
            this.timeResult = timeResult;
        }
    }
}
