package com.baidubce.services.bcc.model.reversed;

import lombok.Getter;

/**
 * osType|purchaseCount|effectiveTime|expireTime
 * 排序key
 */
@Getter
public enum SortKey {

    osType("os_type"),

    instanceCount("purchase_count"),

    effectiveTime("effective_time"),

    expireTime("expire_time");

    SortKey(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
