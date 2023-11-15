package com.baidubce.services.cdn.model.stat;

import java.util.List;

public class ErrorCodeCounter {
    /**
     * 错误码
     */
    private Integer code;
    private List<ErrorCounterDetail> counters;

    public ErrorCodeCounter() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<ErrorCounterDetail> getCounters() {
        return counters;
    }

    public void setCounters(List<ErrorCounterDetail> counters) {
        this.counters = counters;
    }
}
