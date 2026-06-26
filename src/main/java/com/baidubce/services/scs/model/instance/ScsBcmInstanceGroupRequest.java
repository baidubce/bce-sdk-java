package com.baidubce.services.scs.model.instance;

/**
 * Scs bcm instance group request item.
 */
public class ScsBcmInstanceGroupRequest {

    private String groupIds;
    private String groupResourceType;

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getGroupResourceType() {
        return groupResourceType;
    }

    public void setGroupResourceType(String groupResourceType) {
        this.groupResourceType = groupResourceType;
    }
}
