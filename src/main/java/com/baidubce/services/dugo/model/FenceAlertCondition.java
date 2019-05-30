/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Condition for fence alert
 */
public enum FenceAlertCondition {
    IN("IN"), OUT("OUT"), IN_OUT("IN_OUT");

    private String alertCondition;

    FenceAlertCondition(String alertCondition) {
        this.alertCondition = alertCondition;
    }

    public String getAlertCondition() {
        return alertCondition;
    }
}
