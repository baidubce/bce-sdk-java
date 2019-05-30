/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Alarm sink type
 */
public enum AlarmSinkType {
    KAFKA("KAFKA");

    String sinkType;

    AlarmSinkType(String sinkType) {
        this.sinkType = sinkType;
    }

    public String getValue() {
        return this.sinkType;
    }
}
