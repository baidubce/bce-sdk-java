package com.baidubce.services.rds.model;

/**
 * The mapping of topology read replica
 */
public class RdsTopologyReadReplica {

    private String appId;
    private String appIdShort;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppIdShort() {
        return appIdShort;
    }

    public void setAppIdShort(String appIdShort) {
        this.appIdShort = appIdShort;
    }
}
