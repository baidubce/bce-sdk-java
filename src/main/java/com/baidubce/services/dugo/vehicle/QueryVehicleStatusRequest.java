/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * query the vehicle status online
 * Created by liuzhenxing01 on 2018/10/31.
 */
public class QueryVehicleStatusRequest extends AbstractDuGoRequest {
    private List<String> vehicleIds;

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
