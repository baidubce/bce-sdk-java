package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/10/18.
 */
public class GetAllDomainsBandwidthResponse extends AbstractBceResponse {

    private String startTime = null;

    private String endTime = null;

    private String timeInterval = null;

    private List<BandwidthStatistics> statistics = null;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public List<BandwidthStatistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<BandwidthStatistics> statistics) {
        this.statistics = statistics;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetAllDomainsBandwidthResponse {\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    timeInterval: ").append(timeInterval).append("\n");
        sb.append("    statistics: ").append(statistics).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    public static class BandwidthStatistics {

        private String timestamp = null;

        private Long bandwidthInBps = null;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public Long getBandwidthInBps() {
            return bandwidthInBps;
        }

        public void setBandwidthInBps(Long bandwidthInBps) {
            this.bandwidthInBps = bandwidthInBps;
        }

        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("class PlayCountStatistics {\n");
            sb.append("    timestamp: ").append(timestamp).append("\n");
            sb.append("    bandwidthInBps: ").append(bandwidthInBps).append("\n");
            sb.append("}\n");
            return sb.toString();
        }
    }
}
