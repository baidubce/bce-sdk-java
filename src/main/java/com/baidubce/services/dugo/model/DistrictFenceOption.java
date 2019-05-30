/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.model;

/**
 * Option for district fence
 */
public class DistrictFenceOption {
    private String district;
    private Integer denoise;

    public DistrictFenceOption(String district) {
        this.district = district;
    }

    public DistrictFenceOption(String district, Integer denoise) {
        this.district = district;
        this.denoise = denoise;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getDenoise() {
        return denoise;
    }

    public void setDenoise(Integer denoise) {
        this.denoise = denoise;
    }
}
