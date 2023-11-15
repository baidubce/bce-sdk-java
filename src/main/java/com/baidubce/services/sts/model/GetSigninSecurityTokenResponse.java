package com.baidubce.services.sts.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Date;

public class GetSigninSecurityTokenResponse extends AbstractBceResponse {

    private String userId;

    private String sessionToken;

    private Date expiration;

    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SigninSecurityToken{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", sessionToken='").append(sessionToken).append('\'');
        sb.append(", expiration=").append(expiration);
        sb.append('}');
        return sb.toString();
    }
}
