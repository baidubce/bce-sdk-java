package com.baidubce.services.bmr.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Schedule {

    private Integer period;

    private String periodUnit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC") private Date
            startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC") private Date
            endTime;

    /**
     * 构造方法
     */
    public Schedule() {

    }

    public Schedule withPeriod(int period) {
        this.setPeriod(period);
        return this;
    }

    public Schedule withPeriodUnit(String periodUnit) {
        this.setPeriodUnit(periodUnit);
        return this;
    }

    public Schedule withStartTime(Date startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public Schedule withEndTime(Date endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
