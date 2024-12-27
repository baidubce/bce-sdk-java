package com.baidubce.services.bcc.model.reversed;

import lombok.Getter;

/**
 * 排序
 */
@Getter
public enum SortDir {

    /**
     * 升序
     */
    ASC("asc"),

    /**
     * 降序
     */
    DESC("desc");

    SortDir(String value) {
        this.value = value;
    }

    private final String value;

}
