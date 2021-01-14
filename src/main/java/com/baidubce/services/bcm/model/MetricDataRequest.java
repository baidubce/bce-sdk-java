package com.baidubce.services.bcm.model;

import com.baidubce.auth.BceCredentials;

/**
 * The request for query metric data.
 */
public class MetricDataRequest extends BaseMetricDataRequest {

    /**
     * 仅限于使用如下字符集合："0~9、A~Z、a~z"、 "_"
     */
    private String metricName;

    /**
     * 由dimensionName:dimensionValue组成
     * 当监控项具备多个维度时使用分号连接, 如: k1:v1;k2:v2
     * 相同维度只能指定一个维度值
     */
    private String dimensions;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public MetricDataRequest withUserId(String userId) {
        super.userId = userId;
        return this;
    }

    public MetricDataRequest withScope(String scope) {
        this.scope = scope;
        return this;
    }

    public MetricDataRequest withMetricName(String metricName) {
        this.metricName = metricName;
        return this;
    }

    public MetricDataRequest withDimensions(String dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public MetricDataRequest withStatistics(Statistics[] statistics) {
        this.statistics = statistics;
        return this;
    }

    public MetricDataRequest withPeriodInSecond(Integer periodInSecond) {
        this.periodInSecond = periodInSecond;
        return this;
    }

    public MetricDataRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public MetricDataRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    @Override
    public MetricDataRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
