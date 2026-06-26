package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * Request to migrate scs availability zone.
 */
public class ScsMigrateAvailabilityZoneRequest extends AbstractBceRequest {

    private String instanceId;
    private Boolean isDefer;
    private List<ScsReplication> replicationInfo;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Boolean getIsDefer() {
        return isDefer;
    }

    public void setIsDefer(Boolean defer) {
        isDefer = defer;
    }

    public List<ScsReplication> getReplicationInfo() {
        return replicationInfo;
    }

    public void setReplicationInfo(List<ScsReplication> replicationInfo) {
        this.replicationInfo = replicationInfo;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
