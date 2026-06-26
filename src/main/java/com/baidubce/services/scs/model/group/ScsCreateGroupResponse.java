package com.baidubce.services.scs.model.group;

import com.baidubce.model.AbstractBceResponse;

public class ScsCreateGroupResponse extends AbstractBceResponse {
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
