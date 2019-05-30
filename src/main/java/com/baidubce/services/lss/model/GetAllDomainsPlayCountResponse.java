package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuyafei on 16/10/18.
 */
public class GetAllDomainsPlayCountResponse extends AbstractBceResponse {

    private String startTime = null;

    private String endTime = null;

    private String timeInterval = null;

    private List<PlayCountStatistics> hlsStatistics = null;

    private List<PlayCountStatistics> flvStatistics = null;

    private List<PlayCountStatistics> rtmpStatistics = null;

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

    public List<PlayCountStatistics> getHlsStatistics() {
        return hlsStatistics;
    }

    public void setHlsStatistics(List<PlayCountStatistics> hlsStatistics) {
        this.hlsStatistics = hlsStatistics;
    }

    public List<PlayCountStatistics> getFlvStatistics() {
        return flvStatistics;
    }

    public void setFlvStatistics(List<PlayCountStatistics> flvStatistics) {
        this.flvStatistics = flvStatistics;
    }

    public List<PlayCountStatistics> getRtmpStatistics() {
        return rtmpStatistics;
    }

    public void setRtmpStatistics(List<PlayCountStatistics> rtmpStatistics) {
        this.rtmpStatistics = rtmpStatistics;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetAllDomainsPlayCountResponse {\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    timeInterval: ").append(timeInterval).append("\n");
        sb.append("    flvStatistics: ").append(flvStatistics).append("\n");
        sb.append("    hlsStatistics: ").append(hlsStatistics).append("\n");
        sb.append("    rtmpStatistics: ").append(rtmpStatistics).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    public static class PlayCountStatistics implements Serializable {
        private String timestamp = null;

        private Long playCount = null;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public Long getPlayCount() {
            return playCount;
        }

        public void setPlayCount(Long playCount) {
            this.playCount = playCount;
        }

        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("class PlayCountStatistics {\n");
            sb.append("    timestamp: ").append(timestamp).append("\n");
            sb.append("    playCount: ").append(playCount).append("\n");
            sb.append("}\n");
            return sb.toString();
        }
    }
}
