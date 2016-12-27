package com.baidubce.services.lss.model;

/**
 * Created by wuyafei on 16/10/18.
 */
public class GetOneDomainBandwidthResponse extends GetAllDomainsBandwidthResponse {

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetOneDomainsBandwidthResponse {\n");
        sb.append("    startTime: ").append(this.getStartTime()).append("\n");
        sb.append("    endTime: ").append(this.getEndTime()).append("\n");
        sb.append("    timeInterval: ").append(this.getTimeInterval()).append("\n");
        sb.append("    statistics: ").append(this.getStatistics()).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
