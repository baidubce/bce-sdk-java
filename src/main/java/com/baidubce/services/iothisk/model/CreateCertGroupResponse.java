package com.baidubce.services.iothisk.model;

/**
 * Represent the response of create cert group.
 */
public class CreateCertGroupResponse extends IotPkiManageResponse {

    /**
     * Group ID of the group.
     */
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
