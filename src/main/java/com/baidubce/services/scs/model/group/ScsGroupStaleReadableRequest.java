package com.baidubce.services.scs.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ScsGroupStaleReadableRequest extends AbstractBceRequest {
    private String followerId;
    private Boolean staleReadable;

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public Boolean getStaleReadable() {
        return staleReadable;
    }

    public void setStaleReadable(Boolean staleReadable) {
        this.staleReadable = staleReadable;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
