package com.baidubce.services.bcm.model;

import com.baidubce.auth.BceCredentials;

/**
 * The request for query metric data.
 */
public class ListMetricDataRequest extends BaseMetricDataRequest {

    /**
     * 仅限于使用如下字符集合："0~9、A~Z、a~z"、 "_"
     */
    private String[] metricNames;

    /**
     * 由dimensionName:dimensionValue组成
     * 当监控项具备多个维度时使用分号连接, 如: k1:v1;k2:v2
     * 相同维度只能指定一个维度值
     */
    private String dimensions;

    public String[] getMetricNames() {
        return metricNames;
    }

    public void setMetricNames(String[] metricNames) {
        this.metricNames = metricNames;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public ListMetricDataRequest withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ListMetricDataRequest withScope(String scope) {
        this.scope = scope;
        return this;
    }

    public ListMetricDataRequest withMetricNames(String[] metricNames) {
        this.metricNames = metricNames;
        return this;
    }

    public ListMetricDataRequest withDimensions(String dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public ListMetricDataRequest withStatistics(Statistics[] statistics) {
        this.statistics = statistics;
        return this;
    }

    public ListMetricDataRequest withPeriodInSecond(Integer periodInSecond) {
        this.periodInSecond = periodInSecond;
        return this;
    }

    public ListMetricDataRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public ListMetricDataRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    @Override
    public ListMetricDataRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
