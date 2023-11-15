package com.baidubce.services.scs.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request to make slow log enable
 */
public class ScsSlowLogModifyRequest extends AbstractBceRequest {

    private String instanceId;
    private ScsSlowLogAction action;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public ScsSlowLogAction getAction() {
        return action;
    }

    public void setAction(ScsSlowLogAction action) {
        this.action = action;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
