package com.baidubce.services.tsdb.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Task type enum.
 *
 * @author zhangweiliang
 */
@RequiredArgsConstructor
public enum TaskType {
    EXPORT("Export"),

    DELETE("Delete");

    @Getter
    private final String type;
}
