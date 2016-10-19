package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/7/11.
 */
public class GetSessionStatisticsRequest extends AbstractBceRequest {
    private String sessionId;

    private String startDate;

    private String endDate;

    private Boolean aggregate;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean getAggregate() {
        return aggregate;
    }

    public void setAggregate(Boolean aggregate) {
        this.aggregate = aggregate;
    }

    public GetSessionStatisticsRequest withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public GetSessionStatisticsRequest withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public GetSessionStatisticsRequest withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public GetSessionStatisticsRequest withAggregate(Boolean aggregate) {
        this.aggregate = aggregate;
        return this;
    }

    @Override
    public GetSessionStatisticsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetSessionStatisticsRequest {\n");
        sb.append("    sessionId: ").append(sessionId).append("\n");
        sb.append("    startDate: ").append(startDate).append("\n");
        sb.append("    endDate: ").append(endDate).append("\n");
        sb.append("    aggregate: ").append(aggregate).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
