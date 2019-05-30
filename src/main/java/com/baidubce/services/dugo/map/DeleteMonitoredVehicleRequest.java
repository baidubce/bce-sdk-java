/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * Request for deleting monitored vehicles
 */
public class DeleteMonitoredVehicleRequest extends AbstractDuGoRequest {
    private String projectId;
    private List<String> vehicleIdList;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<String> getVehicleIdList() {
        return vehicleIdList;
    }

    public void setVehicleIdList(List<String> vehicleIdList) {
        this.vehicleIdList = vehicleIdList;
    }
}
