package com.baidubce.services.lss.model;

/**
 * Created by wuyafei on 16/10/17.
 */
public class LiveStatistics {

    private Long durationInMinute = null;

    private Long peakPlayCount = null;

    private Long peakBandwidthInBps = null;

    private Long downstreamInByte = null;

    private Long playCount = null;

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

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveStatistics {\n");
        sb.append("    durationInMinutes: ").append(durationInMinute).append("\n");
        sb.append("    peakPlayCount: ").append(peakPlayCount).append("\n");
        sb.append("    peakBandwidthInBps: ").append(peakBandwidthInBps).append("\n");
        sb.append("    downstreamInBytes: ").append(downstreamInByte).append("\n");
        sb.append("    playCount: ").append(playCount).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
