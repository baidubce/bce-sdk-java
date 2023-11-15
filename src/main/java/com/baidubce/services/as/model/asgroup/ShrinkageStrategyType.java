/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.as.model.asgroup;

public enum ShrinkageStrategyType {

    Earlier("earlier"),

    Later("later");

    private String value;

    ShrinkageStrategyType(String value) {
        this.value = value;
    }

    public static boolean contain(String value) {
        ShrinkageStrategyType[] shrinkageStrategyTypes = values();
        for (ShrinkageStrategyType type : shrinkageStrategyTypes) {
            if (type.value.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
