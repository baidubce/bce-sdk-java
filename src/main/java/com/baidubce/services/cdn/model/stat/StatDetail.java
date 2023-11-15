package com.baidubce.services.cdn.model.stat;

import java.util.List;

public class StatDetail {

    /**
     * 时间点 UTC时间
     */
    private String timestamp;

    /**
     * 聚合粒度（groupBy）：key => key，其他 => total
     */
    private String key;

    /**
     * 客户端访问分布数据
     */
    private List<DistributionData> distribution;

    /**
     * HTTP状态码计数
     */
    private List<CounterDetail> counters;

    /**
     * 统计时间段内的平均速率
     */
    private Long avgspeed;

    /**
     * 请求量
     */
    private Long pv;

    /**
     * 平均qps
     */
    private Long qps;

    /**
     * 统计时间段内的请求量
     */
    private Long uv;

    /**
     * 统计时间段内的传输字节数
     */
    private Long flow;

    /**
     * 统计时间段内的平均bps
     */
    private Long bps;

    /**
     * 统计时间段内的字节命中率
     */
    private Double hitrate;

    public StatDetail() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<DistributionData> getDistribution() {
        return distribution;
    }

    public void setDistribution(List<DistributionData> distribution) {
        this.distribution = distribution;
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

    public Long getUv() {
        return uv;
    }

    public void setUv(Long uv) {
        this.uv = uv;
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

    public Double getHitrate() {
        return hitrate;
    }

    public void setHitrate(Double hitrate) {
        this.hitrate = hitrate;
    }

    public List<CounterDetail> getCounters() {
        return counters;
    }

    public void setCounters(List<CounterDetail> counters) {
        this.counters = counters;
    }
}
