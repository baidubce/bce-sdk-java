/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Alarm rule type
 */
public enum AlarmRuleType {
    R1(">"), R2("="), R4("<"), R5(">="), R6("<="), R7("<>");

    String rule;

    AlarmRuleType(String rule) {
        this.rule = rule;
    }

    public String getValue() {
        return rule;
    }
}
