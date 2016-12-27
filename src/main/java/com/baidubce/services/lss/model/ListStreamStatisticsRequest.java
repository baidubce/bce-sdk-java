package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by wuyafei on 16/10/18.
 */
public class ListStreamStatisticsRequest extends AbstractBceRequest {
    
    private String domain = null;
    
    private String app = null;
    
    private String startTime = null;
    
    private String endTime = null;
    
    private String orderBy = null;
    
    private String keywordType = null;
    
    private String keyword = null;

    private Integer pageNo = null;

    private Integer pageSize = null;
    
    public ListStreamStatisticsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ListStreamStatisticsRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public ListStreamStatisticsRequest withApp(String app) {
        this.app = app;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public ListStreamStatisticsRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ListStreamStatisticsRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getKeywordType() {
        return keywordType;
    }

    public void setKeywordType(String keywordType) {
        this.keywordType = keywordType;
    }

    public ListStreamStatisticsRequest withKeywordType(String keywordType) {
        this.keywordType = keywordType;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ListStreamStatisticsRequest withKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public ListStreamStatisticsRequest withOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public ListStreamStatisticsRequest withPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ListStreamStatisticsRequest withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListStreamStatisticsRequest {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    startTime: ").append(startTime).append("\n");
        sb.append("    endTime: ").append(endTime).append("\n");
        sb.append("    orderBy: ").append(orderBy).append("\n");
        sb.append("    keyword: ").append(keyword).append("\n");
        sb.append("    keywordType: ").append(keywordType).append("\n");
        sb.append("    pageNo: ").append(pageNo).append("\n");
        sb.append("    pageSize: ").append(pageSize).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
