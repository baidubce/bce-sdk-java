package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/18.
 */
public class GetAllDomainsStatisticsRequest extends AbstractBceRequest {

    private String startTime = null;

    private String endTime = null;

    private String timeInterval = null;

    @Override
    public GetAllDomainsStatisticsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public GetAllDomainsStatisticsRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GetAllDomainsStatisticsRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public GetAllDomainsStatisticsRequest withTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetAllDomainsStatisticsRequest {\n");
        sb.append("    app: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    timeInterval: ").append(timeInterval).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
