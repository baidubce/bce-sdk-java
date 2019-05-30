/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response for getting latest point
 */
public class GetLatestPointResponse extends AbstractBceResponse {
    private GeoInfo geoInfo;
    private Double limitSpeed;

    public GeoInfo getGeoInfo() {
        return geoInfo;
    }

    public void setGeoInfo(GeoInfo geoInfo) {
        this.geoInfo = geoInfo;
    }

    public Double getLimitSpeed() {
        return limitSpeed;
    }

    public void setLimitSpeed(Double limitSpeed) {
        this.limitSpeed = limitSpeed;
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
