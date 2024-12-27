package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of Update instance connection information
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsConnInformationRequest extends AbstractBceRequest {
    private String instanceId;
    private String address;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
