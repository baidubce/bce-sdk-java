/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;
import java.util.Map;

/**
 * Batch bind vehicles request
 */
public class BatchBindVehicleRequest extends AbstractDuGoRequest {
    private String batchId;
    private List<String> vehicleIds;
    // needed only for GB/T 32960 protocol vehicles
    private Map<String, String> iccids;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public Map<String, String> getIccids() {
        return iccids;
    }

    public void setIccids(Map<String, String> iccids) {
        this.iccids = iccids;
    }
}
