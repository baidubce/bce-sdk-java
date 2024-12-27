package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of Instance Update storage automatic expansion configuration
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsUpdateStorageAutoExpansionConfigRequest extends AbstractBceRequest {
    private String instanceId;
    private String action;
    Integer freeSpaceThreshold; Integer diskMaxLimit;
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getFreeSpaceThreshold() {
        return freeSpaceThreshold;
    }

    public void setFreeSpaceThreshold(Integer freeSpaceThreshold) {
        this.freeSpaceThreshold = freeSpaceThreshold;
    }

    public Integer getDiskMaxLimit() {
        return diskMaxLimit;
    }

    public void setDiskMaxLimit(Integer diskMaxLimit) {
        this.diskMaxLimit = diskMaxLimit;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
