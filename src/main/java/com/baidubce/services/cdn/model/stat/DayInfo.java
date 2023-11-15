package com.baidubce.services.cdn.model.stat;

public class DayInfo {
    private String date;
    private Long totalFlow;
    private Long peakBandwidth;
    private String peakBandwidthTime;

    public DayInfo() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPeakBandwidthTime() {
        return peakBandwidthTime;
    }

    public void setPeakBandwidthTime(String peakBandwidthTime) {
        this.peakBandwidthTime = peakBandwidthTime;
    }
}
