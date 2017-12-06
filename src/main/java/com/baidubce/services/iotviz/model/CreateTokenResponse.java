package com.baidubce.services.iotviz.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response of token create result
 */
public class CreateTokenResponse extends AbstractBceResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
