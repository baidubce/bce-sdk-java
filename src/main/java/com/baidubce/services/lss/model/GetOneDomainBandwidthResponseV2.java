package com.baidubce.services.lss.model;

public class GetOneDomainBandwidthResponseV2 extends GetAllDomainsBandwidthResponseV2 {

    private String domain = null;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetOneDomainTrafficResponseV2 {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    startTime: ").append(this.getStartTime()).append("\n");
        sb.append("    endTime: ").append(this.getEndTime()).append("\n");
        sb.append("    timeInterval: ").append(this.getTimeInterval()).append("\n");
        sb.append("    statistics: ").append(this.getStatistics()).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}