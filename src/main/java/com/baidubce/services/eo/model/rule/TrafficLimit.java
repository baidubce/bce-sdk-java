package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The single connection traffic limit configuration.
 */
public class TrafficLimit extends JsonObject {
    /**
     * Whether to enable rate limiting. true means enabled, false means disabled.
     */
    private Boolean enable;

    /**
     * The rate limit in Byte/s. Required when enabled.
     */
    private Integer limitRate;

    /**
     * The rate limit start hour, 0-24, less than the end hour.
     */
    private Integer limitStartHour;

    /**
     * The rate limit end hour, 0-24, greater than the start hour.
     */
    private Integer limitEndHour;

    /**
     * The default rate limit unit. Valid values are "k", "m", "g".
     */
    private String limitRateUnit;

    /**
     * @param enable
     * @return this object
     */
    public TrafficLimit withEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    /**
     * @return enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * @param enable
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * @param limitRate
     * @return this object
     */
    public TrafficLimit withLimitRate(Integer limitRate) {
        this.limitRate = limitRate;
        return this;
    }

    /**
     * @return limitRate
     */
    public Integer getLimitRate() {
        return limitRate;
    }

    /**
     * @param limitRate
     */
    public void setLimitRate(Integer limitRate) {
        this.limitRate = limitRate;
    }

    /**
     * @param limitStartHour
     * @return this object
     */
    public TrafficLimit withLimitStartHour(Integer limitStartHour) {
        this.limitStartHour = limitStartHour;
        return this;
    }

    /**
     * @return limitStartHour
     */
    public Integer getLimitStartHour() {
        return limitStartHour;
    }

    /**
     * @param limitStartHour
     */
    public void setLimitStartHour(Integer limitStartHour) {
        this.limitStartHour = limitStartHour;
    }

    /**
     * @param limitEndHour
     * @return this object
     */
    public TrafficLimit withLimitEndHour(Integer limitEndHour) {
        this.limitEndHour = limitEndHour;
        return this;
    }

    /**
     * @return limitEndHour
     */
    public Integer getLimitEndHour() {
        return limitEndHour;
    }

    /**
     * @param limitEndHour
     */
    public void setLimitEndHour(Integer limitEndHour) {
        this.limitEndHour = limitEndHour;
    }

    /**
     * @param limitRateUnit
     * @return this object
     */
    public TrafficLimit withLimitRateUnit(String limitRateUnit) {
        this.limitRateUnit = limitRateUnit;
        return this;
    }

    /**
     * @return limitRateUnit
     */
    public String getLimitRateUnit() {
        return limitRateUnit;
    }

    /**
     * @param limitRateUnit
     */
    public void setLimitRateUnit(String limitRateUnit) {
        this.limitRateUnit = limitRateUnit;
    }
}
