package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnRequest;

public class GetMonth95Request extends CdnRequest {

    /**
     * 为','分隔的域名或标签
     */
    private String domains;

    /**
     * 值为peak95，表查询月95带宽数据
     * 必选
     */
    private String type = "peak95";

    /**
     * 表示查询对象为tag还是domain，默认按 domain 查询，当参数值为 true时表按 tag 查询
     * 可选
     */
    private Boolean withTag;

    /**
     * 表示是否按整月查询，默认按整月查询，当参数值为true时表按输入时间查询
     * 可选
     */
    private Boolean byTime;

    /**
     * 整月查询时的开始查询月份，格式形如"year-month"。默认值为本月
     * 可选
     */
    private String billingMonth;

    /**
     * 开始时间，UTC格式。默认值为当前时间前24小时
     * 可选
     */
    private String startTime;

    /**
     * 结束时间，UTC格式。默认值为当前时间
     * 可选
     */
    private String endTime;

    public GetMonth95Request() {
    }

    public GetMonth95Request withDomains(String domains) {
        this.domains = domains;
        return this;
    }

    public GetMonth95Request withType(String type) {
        this.type = type;
        return this;
    }

    public GetMonth95Request withBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
        return this;
    }

    public GetMonth95Request withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public GetMonth95Request withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public GetMonth95Request withByTime(Boolean byTime) {
        this.byTime = byTime;
        return this;
    }

    public GetMonth95Request withWithTag(Boolean withTag) {
        this.withTag = withTag;
        return this;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getWithTag() {
        return withTag;
    }

    public void setWithTag(Boolean withTag) {
        this.withTag = withTag;
    }

    public Boolean getByTime() {
        return byTime;
    }

    public void setByTime(Boolean byTime) {
        this.byTime = byTime;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
