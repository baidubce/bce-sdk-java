package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/10/17.
 */
public class GetDomainStatisticsResponse extends AbstractBceResponse {

    private String domain = null;

    private String startDate = null;

    private String endDate = null;

    private List<LiveStatisticsWithDate> statistics = null;

    private LiveStatistics aggregate = null;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public List<LiveStatisticsWithDate> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<LiveStatisticsWithDate> statistics) {
        this.statistics = statistics;
    }

    public LiveStatistics getAggregate() {
        return aggregate;
    }

    public void setAggregate(LiveStatistics aggregate) {
        this.aggregate = aggregate;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetDomainStatisticsResponse {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    startDate: ").append(startDate).append("\n");
        sb.append("    endDate: ").append(endDate).append("\n");
        sb.append("    statistics: ").append(statistics).append("\n");
        sb.append("    aggregate: ").append(aggregate).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
