package com.baidubce.services.lss.model;

/**
 * Created by wuyafei on 16/10/17.
 */
public class LiveStatisticsWithDate extends LiveStatistics {

    private String date = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveStatisticsWithDate {\n");
        sb.append("    date: ").append(this.getDate()).append("\n");
        sb.append("    durationInMinutes: ").append(this.getDurationInMinute()).append("\n");
        sb.append("    peakPlayCount: ").append(this.getPeakPlayCount()).append("\n");
        sb.append("    peakBandwidthInBps: ").append(this.getPeakBandwidthInBps()).append("\n");
        sb.append("    downstreamInBytes: ").append(this.getDownstreamInByte()).append("\n");
        sb.append("    playCount: ").append(this.getPlayCount()).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
