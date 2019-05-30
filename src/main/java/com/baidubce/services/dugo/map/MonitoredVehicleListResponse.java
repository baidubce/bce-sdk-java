/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response for querying monitored vehicles
 */
public class MonitoredVehicleListResponse extends AbstractBceResponse {
    private long total;
    private List<VehicleDigest> vehicleDigestList;

    public MonitoredVehicleListResponse() {
    }

    public MonitoredVehicleListResponse(long total, List<VehicleDigest> vehicleDigestList) {
        this.total = total;
        this.vehicleDigestList = vehicleDigestList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<VehicleDigest> getVehicleDigestList() {
        return vehicleDigestList;
    }

    public void setVehicleDigestList(List<VehicleDigest> vehicleDigestList) {
        this.vehicleDigestList = vehicleDigestList;
    }

    public static class VehicleDigest {
        private String vehicleId;
        private String alertCondition;

        public VehicleDigest() {
        }

        public VehicleDigest(String vehicleId, String alertCondition) {
            this.vehicleId = vehicleId;
            this.alertCondition = alertCondition;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getAlertCondition() {
            return alertCondition;
        }

        public void setAlertCondition(String alertCondition) {
            this.alertCondition = alertCondition;
        }
    }
}
