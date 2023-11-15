package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetAllDomainsStatisticsRequestV2 extends AbstractBceRequest {

    private String startTime = null;

    private String endTime = null;

    private String timeInterval = null;

    private Boolean allDomain = null;

    @Override
    public GetAllDomainsStatisticsRequestV2 withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public GetAllDomainsStatisticsRequestV2 withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GetAllDomainsStatisticsRequestV2 withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public GetAllDomainsStatisticsRequestV2 withTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
        return this;
    }

    public Boolean getAllDomain() {
        return allDomain;
    }

    public void setAllDomain(Boolean allDomain) {
        this.allDomain = allDomain;
    }

    public GetAllDomainsStatisticsRequestV2 withAllDomain(Boolean allDomain) {
        this.allDomain = allDomain;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetAllDomainsStatisticsRequestV2 {\n");
        sb.append("    app: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    timeInterval: ").append(timeInterval).append("\n");
        sb.append("    allDomain: ").append(allDomain).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}