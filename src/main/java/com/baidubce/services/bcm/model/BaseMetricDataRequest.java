package com.baidubce.services.bcm.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Base Metric Data Request
 */
public abstract class BaseMetricDataRequest extends AbstractBceRequest {

    /**
     * 主账号id
     */
    public String userId;

    /**
     * 仅限于使用如下字符集合："0~9、A~Z、a~z"、 "_"
     */
    public String scope;

    /**
     * Statistics
     */
    public Statistics[] statistics;

    /**
     * DateTime,请参考日期与时间，UTC日期表示
     */
    public String startTime;

    /**
     * DateTime,请参考日期与时间，UTC日期表示
     */
    public String endTime;

    /**
     * Integer，60的倍数，单位：秒（s）
     */
    public Integer periodInSecond;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Statistics[] getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics[] statistics) {
        this.statistics = statistics;
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

    public Integer getPeriodInSecond() {
        return periodInSecond;
    }

    public void setPeriodInSecond(Integer periodInSecond) {
        this.periodInSecond = periodInSecond;
    }

    @Override
    public BaseMetricDataRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
