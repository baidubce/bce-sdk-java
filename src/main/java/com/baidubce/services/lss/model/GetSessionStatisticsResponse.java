package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/7/11.
 */
public class GetSessionStatisticsResponse extends AbstractBceResponse {
    private String sessionId;

    private String startDate;

    private String endDate;

    private SessionAggregate aggregate;

    List<InnerSessionStatistics> statistics = null;

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

    public SessionAggregate getAggregate() {
        return aggregate;
    }

    public void setAggregate(SessionAggregate aggregate) {
        this.aggregate = aggregate;
    }

    public List<InnerSessionStatistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<InnerSessionStatistics> statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetSessionResponse {\n");
        sb.append("    sessionId: ").append(sessionId).append("\n");
        sb.append("    startDate: ").append(startDate).append("\n");
        sb.append("    endDate: ").append(endDate).append("\n");
        sb.append("    aggregate: ").append(aggregate).append("\n");
        sb.append("    statistics: ").append(statistics).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
