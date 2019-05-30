/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Alert type
 */
public enum AlertType {
    EVERY_TIME("EVERY_TIME");

    private String alertType;

    AlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getValue() {
        return alertType;
    }
}
