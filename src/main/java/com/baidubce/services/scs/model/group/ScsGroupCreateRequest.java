package com.baidubce.services.scs.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ScsGroupCreateRequest extends AbstractBceRequest {
    private ScsGroupLeaderInfo leader;

    public ScsGroupLeaderInfo getLeader() {
        return leader;
    }

    public void setLeader(ScsGroupLeaderInfo leader) {
        this.leader = leader;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
