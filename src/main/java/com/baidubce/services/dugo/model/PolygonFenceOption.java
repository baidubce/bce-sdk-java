/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

import java.util.List;

/**
 * Option for polygon fence
 */
public class PolygonFenceOption {
    private List<Point> points;
    private Integer denoise;

    public PolygonFenceOption(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Integer getDenoise() {
        return denoise;
    }

    public void setDenoise(Integer denoise) {
        this.denoise = denoise;
    }

    public static class Point {
        private double longitude;
        private double latitude;

        public Point(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }
}
