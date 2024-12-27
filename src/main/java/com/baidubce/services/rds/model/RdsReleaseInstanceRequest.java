package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Release the specific rds instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsReleaseInstanceRequest extends AbstractBceRequest {

    private List<String> instanceIds = new ArrayList<String>();

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
