package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ScsSyncGroupMemberRequest extends AbstractBceRequest {
    private String memberId;
    private String region;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
