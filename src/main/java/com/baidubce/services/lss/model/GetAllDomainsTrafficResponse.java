package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuyafei on 16/10/18.
 */
public class GetAllDomainsTrafficResponse extends AbstractBceResponse {

    private String startTime = null;

    private String endTime = null;

    private String timeInterval = null;

    private List<TrafficStatistics> statistics = null;

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

    public List<TrafficStatistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<TrafficStatistics> statistics) {
        this.statistics = statistics;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetAllDomainsTrafficResponse {\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    timeInterval: ").append(timeInterval).append("\n");
        sb.append("    statistics: ").append(statistics).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    public static class TrafficStatistics implements Serializable {

        private String timestamp = null;

        private Long downstreamInByte = null;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public Long getDownstreamInByte() {
            return downstreamInByte;
        }

        public void setDownstreamInByte(Long downstreamInByte) {
            this.downstreamInByte = downstreamInByte;
        }

        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("class PlayCountStatistics {\n");
            sb.append("    timestamp: ").append(timestamp).append("\n");
            sb.append("    downstreamInByte: ").append(downstreamInByte).append("\n");
            sb.append("}\n");
            return sb.toString();
        }
    }

}
