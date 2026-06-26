package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ScsSyncGroupPreCheckRequest extends AbstractBceRequest {
    private String syncGroupShowId;
    private List<ScsSyncGroupMemberInfo> members;

    public String getSyncGroupShowId() {
        return syncGroupShowId;
    }

    public void setSyncGroupShowId(String syncGroupShowId) {
        this.syncGroupShowId = syncGroupShowId;
    }

    public List<ScsSyncGroupMemberInfo> getMembers() {
        return members;
    }

    public void setMembers(List<ScsSyncGroupMemberInfo> members) {
        this.members = members;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
