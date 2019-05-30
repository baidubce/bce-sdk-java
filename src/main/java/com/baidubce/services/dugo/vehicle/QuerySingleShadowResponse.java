/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Response for querying single shadow
 */
public class QuerySingleShadowResponse extends AbstractBceResponse {
    private String vehicleId;
    private JsonNode data;
    private JsonNode lastUpdatedTime;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(JsonNode lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
