/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Fence type
 */
public enum FenceType {
    CIRCLE("CIRCLE"), POLYGON("POLYGON"), POLYLINE("POLYLINE"), DISTRICT("DISTRICT");

    private String fenceType;

    FenceType(String fenceType) {
        this.fenceType = fenceType;
    }

    public String getFenceType() {
        return fenceType;
    }
}
