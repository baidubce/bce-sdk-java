package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import java.util.List;

public class DeleteInstanceDeploysetRequest extends AbstractBceRequest {
    private String deployId;
    private List<String> instanceIdList;

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    public List<String> getInstanceIdList() {
        return instanceIdList;
    }

    public void setInstanceIdList(List<String> instanceIdList) {
        this.instanceIdList = instanceIdList;
    }

    public DeleteInstanceDeploysetRequest withDeployId(String deployId) {
        this.deployId = deployId;
        return this;
    }

    public DeleteInstanceDeploysetRequest withInstanceIdList(List<String> instanceIdList) {
        this.instanceIdList = instanceIdList;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
