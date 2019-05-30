/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response for querying stay points
 */
public class StayPointResponse extends AbstractBceResponse {
    private Integer staypointNum;
    private List<Staypoint> staypoints;

    public Integer getStaypointNum() {
        return staypointNum;
    }

    public void setStaypointNum(Integer staypointNum) {
        this.staypointNum = staypointNum;
    }

    public List<Staypoint> getStaypoints() {
        return staypoints;
    }

    public void setStaypoints(List<Staypoint> staypoints) {
        this.staypoints = staypoints;
    }

    public static class Staypoint {
        private Long startTime;
        private Long endTime;
        private Integer duration;
        private Point point;

        public Long getStartTime() {
            return startTime;
        }

        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        public Long getEndTime() {
            return endTime;
        }

        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }
    }

    public static class Point {
        private Double latitude;
        private Double longitude;
        private Double radius;
        private String coordType;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getRadius() {
            return radius;
        }

        public void setRadius(Double radius) {
            this.radius = radius;
        }

        public String getCoordType() {
            return coordType;
        }

        public void setCoordType(String coordType) {
            this.coordType = coordType;
        }
    }
}
