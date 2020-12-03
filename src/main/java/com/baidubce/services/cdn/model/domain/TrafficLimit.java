package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class TrafficLimit extends JsonObject {
    /**
     * 必选  是否开启限速
     */
    private Boolean enable;
    /**
     * 必选  开启限速时必选，单位Byte/s
     */
    private Integer limitRate;
    /**
     * 可选  限速开始时间，请输入0 - 24范围的数字，小于限速结束时间，默认值为 0
     */
    @DecimalMax(value = "24", message = "limitStartHour取值范围应该是[0,24]")
    @DecimalMin(value = "0", message = "limitStartHour取值范围应该是[0,24]")
    private Integer limitStartHour = 0;
    /**
     * 可选  限速结束时间，请输入0 - 24范围的数字，大于限速开始时间，默认值为 24
     */
    @DecimalMax(value = "24", message = "limitStartHour取值范围应该是[0,24]")
    @DecimalMin(value = "0", message = "limitStartHour取值范围应该是[0,24]")
    private Integer limitEndHour = 24;
    /**
     * 可选 在发送了多少数据之后限速，单位Byte
     */
    private Integer limitRateAfter;
    /**
     * 可选  限速参数名称，根据url中提取的arg进行限速，如rate（优先级高于limitRate
     */
    private String trafficLimitArg;
    /**
     * 可选  限速参数单位，支持m、k、g，默认为Byte
     */
    private String trafficLimitUnit;

    public TrafficLimit() {
    }

    public TrafficLimit(Boolean enable, Integer limitRate) {
        this.enable = enable;
        this.limitRate = limitRate;
    }

    public TrafficLimit withEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public TrafficLimit withLimitRate(Integer limitRate) {
        this.limitRate = limitRate;
        return this;
    }

    public TrafficLimit withLimitStartHour(Integer limitStartHour) {
        this.limitStartHour = limitStartHour;
        return this;
    }

    public TrafficLimit withLimitEndHour(Integer limitEndHour) {
        this.limitEndHour = limitEndHour;
        return this;
    }

    public TrafficLimit withLimitRateAfter(Integer limitRateAfter) {
        this.limitRateAfter = limitRateAfter;
        return this;
    }

    public TrafficLimit withTrafficLimitArg(String trafficLimitArg) {
        this.trafficLimitArg = trafficLimitArg;
        return this;
    }

    public TrafficLimit withTrafficLimitUnit(String trafficLimitUnit) {
        this.trafficLimitUnit = trafficLimitUnit;
        return this;
    }


    public Boolean getEnable() {
        return enable;
    }

    public Integer getLimitRate() {
        return limitRate;
    }

    public Integer getLimitStartHour() {
        return limitStartHour;
    }

    public Integer getLimitEndHour() {
        return limitEndHour;
    }

    public Integer getLimitRateAfter() {
        return limitRateAfter;
    }

    public String getTrafficLimitArg() {
        return trafficLimitArg;
    }

    public String getTrafficLimitUnit() {
        return trafficLimitUnit;
    }
}
