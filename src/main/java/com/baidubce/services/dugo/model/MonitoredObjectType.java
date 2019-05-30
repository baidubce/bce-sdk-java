/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Monitored object type
 */
public enum MonitoredObjectType {
    VEHICLE("VEHICLE"), BATCH("BATCH");

    private String objectType;

    MonitoredObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getValue() {
        return objectType;
    }
}
