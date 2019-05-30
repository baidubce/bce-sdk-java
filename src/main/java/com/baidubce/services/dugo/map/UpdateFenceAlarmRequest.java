/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * Request for updating fence alarms
 */
public class UpdateFenceAlarmRequest extends AbstractDuGoRequest {
    private String projectId;
    private String fenceId;
    private String alertType;
    private List<AlertSink> alertSinkList;

    public UpdateFenceAlarmRequest() {
    }

    public UpdateFenceAlarmRequest(String projectId, String fenceId, String alertType,
                                   List<AlertSink> alertSinkList) {
        this.projectId = projectId;
        this.fenceId = fenceId;
        this.alertType = alertType;
        this.alertSinkList = alertSinkList;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFenceId() {
        return fenceId;
    }

    public void setFenceId(String fenceId) {
        this.fenceId = fenceId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public List<AlertSink> getAlertSinkList() {
        return alertSinkList;
    }

    public void setAlertSinkList(List<AlertSink> alertSinkList) {
        this.alertSinkList = alertSinkList;
    }

    public static class AlertSink {
        private String sinkType;
        private String sinkValue;

        public AlertSink() {
        }

        public AlertSink(String sinkType, String sinkValue) {
            this.sinkType = sinkType;
            this.sinkValue = sinkValue;
        }

        public String getSinkType() {
            return sinkType;
        }

        public void setSinkType(String sinkType) {
            this.sinkType = sinkType;
        }

        public String getSinkValue() {
            return sinkValue;
        }

        public void setSinkValue(String sinkValue) {
            this.sinkValue = sinkValue;
        }
    }

}
