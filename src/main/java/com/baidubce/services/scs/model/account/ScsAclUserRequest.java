package com.baidubce.services.scs.model.account;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Request to manage scs acl user.
 */
public class ScsAclUserRequest extends AbstractBceRequest {

    private String instanceId;
    private String userName;
    private String clientAuth;
    private String extra;
    private Integer userType;

    @JsonIgnore
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientAuth() {
        return clientAuth;
    }

    public void setClientAuth(String clientAuth) {
        this.clientAuth = clientAuth;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
