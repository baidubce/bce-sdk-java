package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/17.
 */
public class GetDomainStatisticsRequest extends AbstractBceRequest {

    private String domain = null;

    private String startDate = null;

    private String endDate = null;

    private Boolean aggregate = null;

    @Override
    public GetDomainStatisticsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public GetDomainStatisticsRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public GetDomainStatisticsRequest withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public GetDomainStatisticsRequest withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public Boolean getAggregate() {
        return aggregate;
    }

    public void setAggregate(Boolean aggregate) {
        this.aggregate = aggregate;
    }

    public GetDomainStatisticsRequest withAggregate(Boolean aggregate) {
        this.aggregate = aggregate;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetDomainStatisticsRequest {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    startDate: ").append(startDate).append("\n");
        sb.append("    endDate: ").append(endDate).append("\n");
        sb.append("    aggregate: ").append(aggregate).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
