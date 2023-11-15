package com.baidubce.services.sts.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetSigninSecurityTokenRequest extends AbstractBceRequest {

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetSigninSecurityTokenRequest() {
    }

    public GetSigninSecurityTokenRequest(String userId) {
        this.userId = userId;
    }

    private String userId;

    @Override
    public GetSigninSecurityTokenRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
