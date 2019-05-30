package com.baidubce.services.lss.model;

import java.io.Serializable;

/**
 * Created by wuyafei on 16/10/18.
 */
public class LiveDomainStatistics implements Serializable {

    private String domain = null;

    private String startDate = null;

    private String endDate = null;

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

    public LiveStatistics getAggregate() {
        return aggregate;
    }

    public void setAggregate(LiveStatistics aggregate) {
        this.aggregate = aggregate;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LiveDomainStatistics {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    startDate: ").append(startDate).append("\n");
        sb.append("    endDate: ").append(endDate).append("\n");
        sb.append("    aggregate: ").append(aggregate).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
