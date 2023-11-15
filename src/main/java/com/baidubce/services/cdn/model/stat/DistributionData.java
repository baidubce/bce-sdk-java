package com.baidubce.services.cdn.model.stat;

import java.util.List;

public class DistributionData {

    /**
     * 客户端所在省份、地区
     */
    private String location;

    /**
     * 客户端所属运营商
     */
    private String isp;

    private Long avgspeed;

    private Long pv;

    private Long qps;

    /**
     * 统计时间段内的传输字节数
     */
    private Long flow;

    /**
     * 统计时间段内的平均bps
     */
    private Long bps;

    /**
     * HTTP状态码计数
     */
    private List<CounterDetail> counters;

    public DistributionData() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public Long getAvgspeed() {
        return avgspeed;
    }

    public void setAvgspeed(Long avgspeed) {
        this.avgspeed = avgspeed;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Long getQps() {
        return qps;
    }

    public void setQps(Long qps) {
        this.qps = qps;
    }

    public Long getFlow() {
        return flow;
    }

    public void setFlow(Long flow) {
        this.flow = flow;
    }

    public Long getBps() {
        return bps;
    }

    public void setBps(Long bps) {
        this.bps = bps;
    }

    public List<CounterDetail> getCounters() {
        return counters;
    }

    public void setCounters(List<CounterDetail> counters) {
        this.counters = counters;
    }
}
