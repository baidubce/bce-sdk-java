/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * multiple shadow request model
 * Created by liuzhenxing01 on 2018/10/18.
 */
public class QueryMultipleShadowRequest extends AbstractDuGoRequest {
    private List<String> vehicleIds;
    private List<String> fields;

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}