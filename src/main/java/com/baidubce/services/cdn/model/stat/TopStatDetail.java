package com.baidubce.services.cdn.model.stat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TopStatDetail {
    /**
     * 时间点 UTC时间
     */
    private String timestamp;

    /**
     * 聚合粒度（groupBy）：key => key，其他 => total
     */
    private String key;

    /**
     * HTTP状态码计数
     */
    private List<TopNDetail> counters;

    @JsonProperty("total_pv")
    private Long totalPv;

    @JsonProperty("total_flow")
    private Long totalFlow;

    public TopStatDetail() {
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

    public List<TopNDetail> getCounters() {
        return counters;
    }

    public void setCounters(List<TopNDetail> counters) {
        this.counters = counters;
    }

    public Long getTotalPv() {
        return totalPv;
    }

    public void setTotalPv(Long totalPv) {
        this.totalPv = totalPv;
    }

    public Long getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(Long totalFlow) {
        this.totalFlow = totalFlow;
    }
}
