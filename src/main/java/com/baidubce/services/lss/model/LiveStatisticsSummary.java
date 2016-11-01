package com.baidubce.services.lss.model;

/**
 * Created by wuyafei on 16/10/17.
 */
public class LiveStatisticsSummary {

    private Long downStreamInByte = null;

    private Long durationInMinute = null;

    private Long playCount = null;

    public Long getDownStreamInByte() {
        return downStreamInByte;
    }

    public void setDownStreamInByte(Long downStreamInByte) {
        this.downStreamInByte = downStreamInByte;
    }

    public Long getDurationInMinute() {
        return durationInMinute;
    }

    public void setDurationInMinute(Long durationInMinute) {
        this.durationInMinute = durationInMinute;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveStatisticsSummary {\n");
        sb.append("    durationInMinutes: ").append(durationInMinute).append("\n");
        sb.append("    downStreamInBytes: ").append(downStreamInByte).append("\n");
        sb.append("    playCount: ").append(playCount).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
