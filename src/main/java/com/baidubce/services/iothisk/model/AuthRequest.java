package com.baidubce.services.iothisk.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for HISK Device authentication.
 */
public class AuthRequest extends AbstractBceRequest {

    /**
     * Device authentication code
     */
    private String authCode;

    /**
     * Device extra message applied in authentication
     */
    private String extra;

    /**
     * Set the device authentication code
     *
     * @param authCode device authentication code
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * Get the device authentication code
     *
     * @return device authentication code
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Set the device extra message applied in authentication
     *
     * @param extra the device extra message applied in authentication
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * Get the device extra message applied in authentication
     *
     * @return get the extra message applied in authentication
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ActiveRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
