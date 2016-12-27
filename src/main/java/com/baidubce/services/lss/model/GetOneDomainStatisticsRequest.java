package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/18.
 */
public class GetOneDomainStatisticsRequest extends AbstractBceRequest  {

    private String domain = null;

    private String startTime = null;

    private String endTime = null;

    private String timeInterval = null;

    @Override
    public GetOneDomainStatisticsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public GetOneDomainStatisticsRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public GetOneDomainStatisticsRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GetOneDomainStatisticsRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String interval) {
        this.timeInterval = interval;
    }

    public GetOneDomainStatisticsRequest withTimeInterval(String interval) {
        this.timeInterval = interval;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetOneDomainStatisticsRequest {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    timeInterval: ").append(timeInterval).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
