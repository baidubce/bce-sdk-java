package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetOneDomainStatisticsRequestV2 extends AbstractBceRequest {

    private String domain = null;

    private String startTime = null;

    private String endTime = null;

    private String timeInterval = null;

    @Override
    public GetOneDomainStatisticsRequestV2 withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public GetOneDomainStatisticsRequestV2 withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public GetOneDomainStatisticsRequestV2 withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GetOneDomainStatisticsRequestV2 withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String interval) {
        this.timeInterval = interval;
    }

    public GetOneDomainStatisticsRequestV2 withTimeInterval(String interval) {
        this.timeInterval = interval;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetOneDomainStatisticsRequestV2 {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    timeInterval: ").append(timeInterval).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

