/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Option for circle fence
 */
public class CircleFenceOption {
    private Double longitude;
    private Double latitude;
    private Double radius;
    private Integer denoise;

    public CircleFenceOption(Double longitude, Double latitude, Double radius) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }

    public CircleFenceOption(Double longitude, Double latitude, Double radius, Integer denoise) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.denoise = denoise;
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

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Integer getDenoise() {
        return denoise;
    }

    public void setDenoise(Integer denoise) {
        this.denoise = denoise;
    }
}
