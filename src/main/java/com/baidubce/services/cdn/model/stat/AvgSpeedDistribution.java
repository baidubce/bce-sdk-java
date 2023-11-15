package com.baidubce.services.cdn.model.stat;

public class AvgSpeedDistribution extends DefaultDistribution {

    private Long avgspeed;

    public AvgSpeedDistribution() {
    }

    public Long getAvgspeed() {
        return avgspeed;
    }

    public void setAvgspeed(Long avgspeed) {
        this.avgspeed = avgspeed;
    }
}
