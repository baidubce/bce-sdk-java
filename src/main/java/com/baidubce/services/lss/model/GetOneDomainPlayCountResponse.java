package com.baidubce.services.lss.model;

/**
 * Created by wuyafei on 16/10/18.
 */
public class GetOneDomainPlayCountResponse extends GetAllDomainsPlayCountResponse {

    private String domain = null;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetOneDomainsPlayCountResponse {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    startTime: ").append(this.getStartTime()).append("\n");
        sb.append("    endTime: ").append(this.getEndTime()).append("\n");
        sb.append("    timeInterval: ").append(this.getTimeInterval()).append("\n");
        sb.append("    flvStatistics: ").append(this.getFlvStatistics()).append("\n");
        sb.append("    hlsStatistics: ").append(this.getHlsStatistics()).append("\n");
        sb.append("    rtmpStatistics: ").append(this.getRtmpStatistics()).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
