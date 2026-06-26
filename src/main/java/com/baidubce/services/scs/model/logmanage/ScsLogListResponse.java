package com.baidubce.services.scs.model.logmanage;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsLogListResponse extends AbstractBceResponse {
    private List<LogItem> logList;

    public List<LogItem> getLogList() {
        return logList;
    }

    public void setLogList(List<LogItem> logList) {
        this.logList = logList;
    }

    public static class LogItem {
        private Integer shardId;
        private String shardShowId;
        private Integer totalNum;
        private List<LogDetail> logItem;

        public Integer getShardId() {
            return shardId;
        }

        public void setShardId(Integer shardId) {
            this.shardId = shardId;
        }

        public String getShardShowId() {
            return shardShowId;
        }

        public void setShardShowId(String shardShowId) {
            this.shardShowId = shardShowId;
        }

        public Integer getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(Integer totalNum) {
            this.totalNum = totalNum;
        }

        public List<LogDetail> getLogItem() {
            return logItem;
        }

        public void setLogItem(List<LogDetail> logItem) {
            this.logItem = logItem;
        }
    }

    public static class LogDetail {
        private String logId;
        private String logShowId;
        private Long logSizeInBytes;
        private String logStartTime;
        private String logEndTime;
        private String downloadUrl;
        private String downloadExpires;

        public String getLogId() {
            return logId;
        }

        public void setLogId(String logId) {
            this.logId = logId;
        }

        public String getLogShowId() {
            return logShowId;
        }

        public void setLogShowId(String logShowId) {
            this.logShowId = logShowId;
        }

        public Long getLogSizeInBytes() {
            return logSizeInBytes;
        }

        public void setLogSizeInBytes(Long logSizeInBytes) {
            this.logSizeInBytes = logSizeInBytes;
        }

        public String getLogStartTime() {
            return logStartTime;
        }

        public void setLogStartTime(String logStartTime) {
            this.logStartTime = logStartTime;
        }

        public String getLogEndTime() {
            return logEndTime;
        }

        public void setLogEndTime(String logEndTime) {
            this.logEndTime = logEndTime;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getDownloadExpires() {
            return downloadExpires;
        }

        public void setDownloadExpires(String downloadExpires) {
            this.downloadExpires = downloadExpires;
        }
    }
}
