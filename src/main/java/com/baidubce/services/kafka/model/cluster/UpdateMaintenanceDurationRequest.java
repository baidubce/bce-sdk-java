package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class UpdateMaintenanceDurationRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    @JsonIgnore
    private String clusterId;

    private List<MaintainPeriod> maintenancePeriods;

    private String maintenanceStartTime;

    private Integer maintenanceDurationHours;

    @Override
    public UpdateMaintenanceDurationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
