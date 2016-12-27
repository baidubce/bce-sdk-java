package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/17.
 */
public class GetDomainSummaryStatisticsRequest extends AbstractBceRequest {

    private String startTime = null;

    private String endTime = null;

    public GetDomainSummaryStatisticsRequest withRequestCredentials(BceCredentials credential) {
        this.setRequestCredentials(credential);
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public GetDomainSummaryStatisticsRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GetDomainSummaryStatisticsRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetDomainSummaryStatisticsRequest {\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
