package com.baidubce.services.scs.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ScsGroupPreCheckRequest extends AbstractBceRequest {
    private ScsGroupLeaderInfo leader;
    private List<ScsGroupFollowerInfo> followers;

    public ScsGroupLeaderInfo getLeader() {
        return leader;
    }

    public void setLeader(ScsGroupLeaderInfo leader) {
        this.leader = leader;
    }

    public List<ScsGroupFollowerInfo> getFollowers() {
        return followers;
    }

    public void setFollowers(List<ScsGroupFollowerInfo> followers) {
        this.followers = followers;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
