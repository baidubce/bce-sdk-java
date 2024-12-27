package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of modify rds parameter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsNetworkStatusRequest extends AbstractBceRequest {
    private String instanceId;
    private boolean publicAccess;

    public String getInstanceId() {
        return instanceId;
    }


    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public boolean isPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
