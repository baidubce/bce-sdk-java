package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/10/18.
 */
public class ListDomainStatisticsResponse extends AbstractBceResponse {

    List<LiveDomainStatistics> domainStatisticsList = null;

    public List<LiveDomainStatistics> getDomainStatisticsList() {
        return domainStatisticsList;
    }

    public void setDomainStatisticsList(List<LiveDomainStatistics> domainStatisticsList) {
        this.domainStatisticsList = domainStatisticsList;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListDomainStatisticsResponse {\n");
        sb.append("    domainStatisticsList: ").append(domainStatisticsList).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
