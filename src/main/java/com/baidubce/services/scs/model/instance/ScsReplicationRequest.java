package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * Request to resize scs replications.
 */
public class ScsReplicationRequest extends AbstractBceRequest {

    private String instanceId;
    private String resizeType;
    private List<ScsReplication> replicationInfo;
    private String clientToken;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getResizeType() {
        return resizeType;
    }

    public void setResizeType(String resizeType) {
        this.resizeType = resizeType;
    }

    public List<ScsReplication> getReplicationInfo() {
        return replicationInfo;
    }

    public void setReplicationInfo(List<ScsReplication> replicationInfo) {
        this.replicationInfo = replicationInfo;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
