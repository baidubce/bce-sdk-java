package com.baidubce.services.lss.model;

import java.io.Serializable;

/**
 * Created by wuyafei on 16/7/11.
 */
public class InnerSessionStatistics implements Serializable {
    private String date = null;

    private Long durationInMinute = null;

    private Long peakPlayCount = null;

    private Long peakBandwidthInBps = null;

    private Long downstreamInByte = null;

    private Long playCount = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getDurationInMinute() {
        return durationInMinute;
    }

    public void setDurationInMinute(Long durationInMinute) {
        this.durationInMinute = durationInMinute;
    }

    public Long getPeakPlayCount() {
        return peakPlayCount;
    }

    public void setPeakPlayCount(Long peakPlayCount) {
        this.peakPlayCount = peakPlayCount;
    }

    public Long getPeakBandwidthInBps() {
        return peakBandwidthInBps;
    }

    public void setPeakBandwidthInBps(Long peakBandwidthInBps) {
        this.peakBandwidthInBps = peakBandwidthInBps;
    }

    public Long getDownstreamInByte() {
        return downstreamInByte;
    }

    public void setDownstreamInByte(Long downstreamInByte) {
        this.downstreamInByte = downstreamInByte;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("    class InnerSessionStatistics {\n");
        sb.append("        date: ").append(date).append("\n");
        sb.append("        durationInMinute: ").append(durationInMinute).append("\n");
        sb.append("        peakPlayCount: ").append(peakPlayCount).append("\n");
        sb.append("        peakBandwidthInBps: ").append(peakBandwidthInBps).append("\n");
        sb.append("        downstreamInByte: ").append(downstreamInByte).append("\n");
        sb.append("        playCount: ").append(playCount).append("\n");
        sb.append("    }\n");
        return sb.toString();
    }
}
