package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/18.
 */
public class ListDomainStatisticsRequest extends AbstractBceRequest {

    private String startTime = null;

    private String endTime = null;

    private String orderBy = null;

    private String keywordType = null;

    private String keyword = null;

    public ListDomainStatisticsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public ListDomainStatisticsRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ListDomainStatisticsRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getKeywordType() {
        return keywordType;
    }

    public void setKeywordType(String keywordType) {
        this.keywordType = keywordType;
    }

    public ListDomainStatisticsRequest withKeywordType(String keywordType) {
        this.keywordType = keywordType;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ListDomainStatisticsRequest withKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public ListDomainStatisticsRequest withOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListDomainStatisticsRequest {\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    orderBy: ").append(orderBy).append("\n");
        sb.append("    keyword: ").append(keyword).append("\n");
        sb.append("    keywordType: ").append(keywordType).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
