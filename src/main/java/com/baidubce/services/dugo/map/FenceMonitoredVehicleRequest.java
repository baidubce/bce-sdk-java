/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.services.dugo.AbstractDuGoRequest;

import java.util.List;

/**
 * Request for querying monitored vehicles
 */
public class FenceMonitoredVehicleRequest extends AbstractDuGoRequest {
    private String projectId;
    private List<VehicleDigest> vehicleDigestList;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
