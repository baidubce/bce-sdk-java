package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * Request to switch scs master and slave.
 */
public class ScsSwitchMasterSlaveRequest extends AbstractBceRequest {

    private String instanceId;
    private List<ScsSwitchMasterSlaveShard> shards;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<ScsSwitchMasterSlaveShard> getShards() {
        return shards;
    }

    public void setShards(List<ScsSwitchMasterSlaveShard> shards) {
        this.shards = shards;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
