package com.baidubce.services.bcm.model.event;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by dongjiawei on 2023/12/12.
 */
public enum EventLevel {
    CRITICAL("CRITICAL"),
    MAJOR("MAJOR"),
    WARNING("WARNING"),
    NOTICE("NOTICE"),
    ALL("*");

    private final String name;

    EventLevel(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
