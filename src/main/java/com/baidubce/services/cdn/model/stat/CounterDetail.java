package com.baidubce.services.cdn.model.stat;

public class CounterDetail {

    /**
     * HTTP状态码
     */
    private Long name;

    /**
     * 统计计数
     */
    private Long count;

    public CounterDetail() {
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
