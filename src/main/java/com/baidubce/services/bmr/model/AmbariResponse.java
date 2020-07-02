package com.baidubce.services.bmr.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response of AmbariPassWordRequest.
 * <p>
 * The response contains a Ambari Password
 */
public class AmbariResponse extends AbstractBceResponse {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
