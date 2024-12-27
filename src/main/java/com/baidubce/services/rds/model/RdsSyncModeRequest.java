package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of Update instance sync mode
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSyncModeRequest extends AbstractBceRequest {


    private String instanceId;
    private String syncMode;

    public String getInstanceId() {
        return instanceId;
    }

    public String getSyncMode() {
        return syncMode;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void setSyncMode(String syncMode) {
        this.syncMode = syncMode;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
