package com.baidubce.services.bmr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class RenameCluseterRequest extends AbstractBceRequest {
    private String clusterId;
    private String newName;

    public RenameCluseterRequest withClusterId(String clusterId) {
        this.setClusterId(clusterId);
        return this;
    }

    public RenameCluseterRequest withNewName(String newName) {
        this.setNewName(newName);
        return this;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    @Override public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
