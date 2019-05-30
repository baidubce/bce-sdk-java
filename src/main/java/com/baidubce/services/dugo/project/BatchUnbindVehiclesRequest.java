/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * Batch unbind vehicles request
 */
public class BatchUnbindVehiclesRequest extends AbstractDuGoRequest {
    private String projectId;
    private List<String> vehicleIds;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
