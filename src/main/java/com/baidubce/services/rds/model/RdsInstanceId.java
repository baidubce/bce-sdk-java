package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Rds InstanceId
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsInstanceId {
    private String instanceId;

    public String getInstanceId() {
        return instanceId;
    }
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
