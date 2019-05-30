/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

import java.util.Date;
import java.util.List;

/**
 * Response for getting fence alarms
 */
public class GetFenceAlarmsResponse extends AbstractBceResponse {
    private Meta meta;
    private List<AlarmPoint> alarmPointList;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<AlarmPoint> getAlarmPointList() {
        return alarmPointList;
    }

    public void setAlarmPointList(List<AlarmPoint> alarmPointList) {
        this.alarmPointList = alarmPointList;
    }

    public static class Meta {
        private long total;
        private String fenceName;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public String getFenceName() {
            return fenceName;
        }

        public void setFenceName(String fenceName) {
            this.fenceName = fenceName;
        }
    }

    public static class AlarmPoint {
        private String vehicleId;
        private Point point;
        private Date alarmTime;
        private String alertCondition;

        public AlarmPoint() {
        }

        public AlarmPoint(String vehicleId, Point point, Date alarmTime, String alertCondition) {
            this.vehicleId = vehicleId;
            this.point = point;
            this.alarmTime = alarmTime;
            this.alertCondition = alertCondition;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }

        public Date getAlarmTime() {
            return alarmTime;
        }

        public void setAlarmTime(Date alarmTime) {
            this.alarmTime = alarmTime;
        }

        public String getAlertCondition() {
            return alertCondition;
        }

        public void setAlertCondition(String alertCondition) {
            this.alertCondition = alertCondition;
        }
    }

    public static class Point {
        private Double longitude;
        private Double latitude;

        public Point() {
        }

        public Point(Double longitude, Double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }
    }
}
