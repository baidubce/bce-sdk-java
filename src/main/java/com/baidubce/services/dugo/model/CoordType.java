/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Coord type
 */
public enum CoordType {
    bd09ll("bd09ll"), wgs84("wgs84"), gcj02("gcj02");

    private String coordType;

    CoordType(String coordType) {
        this.coordType = coordType;
    }

    public String getValue() {
        return coordType;
    }
}
