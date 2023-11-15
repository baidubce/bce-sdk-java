package com.baidubce.services.cdn.model.stat;

public class XcdnDetail {
    /**
     * 时间点 UTC时间
     */
    private String timestamp;

    /**
     * 聚合粒度（groupBy）：key => key，其他 => total
     */
    private String key;

    /**
     * 统计时间段内的传输字节数
     */
    private Long flow;

    /**
     * 统计时间段内的平均bps
     */
    private Long bps;

    public XcdnDetail() {
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
}
