/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response for query vehicle status
 */
public class QueryVehicleStatusResponse extends AbstractBceResponse {
    private List<Vehicle> data;

    public List<Vehicle> getData() {
        return data;
    }

    public void setData(List<Vehicle> data) {
        this.data = data;
    }

    public static class Vehicle {
        private String vehicleId;
        private String status;

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
