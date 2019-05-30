/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * single shadow request and response
 * Created by liuzhenxing01 on 2018/10/18.
 */
public class QuerySingleShadowRequest extends AbstractDuGoRequest {
    private String vehicleId;
    private List<String> fields;
    private boolean needUpdateTime;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public boolean isNeedUpdateTime() {
        return needUpdateTime;
    }

    public void setNeedUpdateTime(boolean needUpdateTime) {
        this.needUpdateTime = needUpdateTime;
    }
}
