package com.baidubce.services.cdn.model.stat;

import java.util.List;

public class AvgSpeedRegionDetail extends Detail {
    private List<AvgSpeedDistribution> distribution;

    public AvgSpeedRegionDetail() {
    }

    public List<AvgSpeedDistribution> getDistribution() {
        return distribution;
    }

    public void setDistribution(List<AvgSpeedDistribution> distribution) {
        this.distribution = distribution;
    }
}
