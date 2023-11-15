package com.baidubce.services.cdn.model.stat;

public class ErrorCounterDetail {
    /**
     * 错误类型
     */
    private String name;

    /**
     * 统计计数
     */
    private Long count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
