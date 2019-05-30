/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response for getting track
 */
public class GetTrackResponse extends AbstractBceResponse {
    private Integer total;
    private Integer size;
    private Double distance;
    private Double tollDistance;
    private LocationPoint startPoint;
    private LocationPoint endPoint;
    private List<GeoInfo> points;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getTollDistance() {
        return tollDistance;
    }

    public void setTollDistance(Double tollDistance) {
        this.tollDistance = tollDistance;
    }

    public LocationPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(LocationPoint startPoint) {
        this.startPoint = startPoint;
    }

    public LocationPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(LocationPoint endPoint) {
        this.endPoint = endPoint;
    }

    public List<GeoInfo> getPoints() {
        return points;
    }

    public void setPoints(List<GeoInfo> points) {
        this.points = points;
    }

    public static class LocationPoint {
        private Double latitude;
        private Double longitude;
        private String coordType;
        private Long locTime;

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

        public String getCoordType() {
            return coordType;
        }

        public void setCoordType(String coordType) {
            this.coordType = coordType;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }
    }

    public static class GeoInfo {
        private Double latitude;
        private Double longitude;
        private Double radius;
        private Double speed;
        private Integer direction;
        private Double height;
        private Long locTime;

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

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }

        public Integer getDirection() {
            return direction;
        }

        public void setDirection(Integer direction) {
            this.direction = direction;
        }

        public Double getHeight() {
            return height;
        }

        public void setHeight(Double height) {
            this.height = height;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }
    }
}
