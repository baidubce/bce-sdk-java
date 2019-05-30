/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Alarm type
 */
public enum AlarmType {
    AT_ONCE("AT_ONCE"),
    AT_ONCE_WITH_NORMAL("AT_ONCE_WITH_NORMAL"),
    MULTI_NOTICE_WITH_NORMAL("MULTI_NOTICE_WITH_NORMAL");

    String alarmType;

    AlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmType() {
        return this.alarmType;
    }
}
