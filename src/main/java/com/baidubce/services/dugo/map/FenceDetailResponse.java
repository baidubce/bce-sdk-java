/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response for querying fence details
 */
public class FenceDetailResponse extends AbstractBceResponse {
    private String fenceId;
    private String fenceName;
    private String fenceType;
    private Object fenceParamsOption;
    private String coordType;
    private String alertType;
    private List<AlertSink> alertSinkList;

    public FenceDetailResponse() {
    }

    public FenceDetailResponse(String fenceId, String fenceName, String fenceType, Object fenceParamsOption,
                               String coordType, String alertType, List<AlertSink> alertSinkList) {
        this.fenceId = fenceId;
        this.fenceName = fenceName;
        this.fenceType = fenceType;
        this.fenceParamsOption = fenceParamsOption;
        this.coordType = coordType;
        this.alertType = alertType;
        this.alertSinkList = alertSinkList;
    }

    public String getFenceId() {
        return fenceId;
    }

    public void setFenceId(String fenceId) {
        this.fenceId = fenceId;
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
