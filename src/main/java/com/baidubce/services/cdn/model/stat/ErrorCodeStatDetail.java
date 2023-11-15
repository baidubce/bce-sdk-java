package com.baidubce.services.cdn.model.stat;

import java.util.List;

public class ErrorCodeStatDetail {

    private String timestamp;
    private String key;
    /**
     * 错误状态码类型及对应计数
     */
    private List<ErrorCodeCounter> counters;

    public ErrorCodeStatDetail() {
    }

    public List<ErrorCodeCounter> getCounters() {
        return counters;
    }

    public void setCounters(List<ErrorCodeCounter> counters) {
        this.counters = counters;
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
}
