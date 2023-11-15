package com.baidubce.services.cdn.model.domain;

public class ConfigKey {
    /**
     * 值为switch || json || rule
     */
    private String type;

    /**
     * 类型为int|bool
     * switch和json对应的value为bool类型，分别表示开关的开闭以及是否已经设置，rule对应的值为int，表规则的数目
     */
    private Object value;

    public ConfigKey() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
