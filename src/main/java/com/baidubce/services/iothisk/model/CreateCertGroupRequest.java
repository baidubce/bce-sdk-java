package com.baidubce.services.iothisk.model;

/**
 * Represent the request for create cert group.
 */
public class CreateCertGroupRequest extends IotPkiManageRequest {

    /**
     * Root cert ID of the group which will be created
     */
    private String rootCertId;

    /**
     * Group name of the group which will be created
     */
    private String groupName;

    public String getRootCertId() {
        return rootCertId;
    }

    public void setRootCertId(String rootCertId) {
        this.rootCertId = rootCertId;
    }

    public CreateCertGroupRequest withRootCertId(String rootCertId) {
        this.rootCertId = rootCertId;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public CreateCertGroupRequest withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

}
