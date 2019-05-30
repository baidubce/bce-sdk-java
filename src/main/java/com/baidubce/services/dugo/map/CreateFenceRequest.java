/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * Create fence request
 */
public class CreateFenceRequest extends AbstractDuGoRequest {
    private String projectId;
    private String fenceName;
    private String fenceType;
    private Object fenceParamsOption;
    private String coordType;
    private List<MonitoredObject> monitoredObjectList;
    private String alertType;
    private List<AlertSink> alertSinkList;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFenceName() {
        return fenceName;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName;
    }

    public String getFenceType() {
        return fenceType;
    }

    public void setFenceType(String fenceType) {
        this.fenceType = fenceType;
    }

    public Object getFenceParamsOption() {
        return fenceParamsOption;
    }

    public void setFenceParamsOption(Object fenceParamsOption) {
        this.fenceParamsOption = fenceParamsOption;
    }

    public String getCoordType() {
        return coordType;
    }

    public void setCoordType(String coordType) {
        this.coordType = coordType;
    }

    public List<MonitoredObject> getMonitoredObjectList() {
        return monitoredObjectList;
    }

    public void setMonitoredObjectList(List<MonitoredObject> monitoredObjectList) {
        this.monitoredObjectList = monitoredObjectList;
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

    public static class MonitoredObject {
        private String objectType;
        private String objectId;
        private String alertCondition;

        public MonitoredObject(String objectType, String objectId, String alertCondition) {
            this.objectType = objectType;
            this.objectId = objectId;
            this.alertCondition = alertCondition;
        }

        public String getObjectType() {
            return objectType;
        }

        public void setObjectType(String objectType) {
            this.objectType = objectType;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getAlertCondition() {
            return alertCondition;
        }

        public void setAlertCondition(String alertCondition) {
            this.alertCondition = alertCondition;
        }
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
