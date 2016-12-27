package com.baidubce.services.vod.model;

import java.util.Date;

public class MediaStatisticsElement {
    private Date timestamp;

    private long playCount;

    private long downstreamInBytes;

    private long peakPlayCount;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public long getDownstreamInBytes() {
        return downstreamInBytes;
    }

    public void setDownstreamInBytes(int downstreamInBytes) {
        this.downstreamInBytes = downstreamInBytes;
    }

    public long getPeakPlayCount() {
        return peakPlayCount;
    }

    public void setPeakPlayCount(int peakPlayCount) {
        this.peakPlayCount = peakPlayCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MediaStatisticsElement { \n");
        if (timestamp != null) {
            sb.append("      timestamp = ").append(timestamp).append("\n");
        }
        sb.append("      playCount = ").append(playCount).append("\n");
        sb.append("      downstreamInBytes = ").append(downstreamInBytes).append("\n");
        sb.append("      peakPlayCount = ").append(peakPlayCount).append("\n");
        sb.append("    }");
        return sb.toString();
    }

}
