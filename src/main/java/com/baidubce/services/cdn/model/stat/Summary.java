package com.baidubce.services.cdn.model.stat;

public class Summary {
    /**
     * 请求时段的总流量
     */
    private Long totalFlow;

    /**
     * 请求时段的峰值带宽
     */
    private Long peakBandwidth;

    public Summary() {
    }

    public Long getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(Long totalFlow) {
        this.totalFlow = totalFlow;
    }

    public Long getPeakBandwidth() {
        return peakBandwidth;
    }

    public void setPeakBandwidth(Long peakBandwidth) {
        this.peakBandwidth = peakBandwidth;
    }
}
