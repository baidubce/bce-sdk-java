package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Request of binding enterprise security groups to a rds instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsBindEnterpriseSecurityRequest extends AbstractBceRequest {

    private String instanceId;

    private List<String> esgUuids;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<String> getEsgUuids() {
        return esgUuids;
    }

    public void setEsgUuids(List<String> esgUuids) {
        this.esgUuids = esgUuids;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
