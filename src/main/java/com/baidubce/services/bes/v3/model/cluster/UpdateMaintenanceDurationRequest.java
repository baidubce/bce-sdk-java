package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateMaintenanceDurationRequest extends AbstractBesV3Request {
    private String clusterId;
    private String maintenanceTimeZone;
    private List<String> maintenancePeriods;
    private String maintenanceStartTime;
    private String maintenanceEndTime;
}
