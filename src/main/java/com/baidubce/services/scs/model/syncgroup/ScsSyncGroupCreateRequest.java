package com.baidubce.services.scs.model.syncgroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ScsSyncGroupCreateRequest extends AbstractBceRequest {
    private String syncGroupName;
    private List<ScsSyncGroupMemberInfo> members;

    public String getSyncGroupName() {
        return syncGroupName;
    }

    public void setSyncGroupName(String syncGroupName) {
        this.syncGroupName = syncGroupName;
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
