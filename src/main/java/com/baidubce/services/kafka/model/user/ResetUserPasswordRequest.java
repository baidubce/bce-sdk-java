package com.baidubce.services.kafka.model.user;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The request for reseting a password of a user.
 */
@Data
public class ResetUserPasswordRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    /**
     * The parameter to specified the username.
     */
    private String username;

    /**
     * The parameter to specified the password.
     */
    private String password;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ResetUserPasswordRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ResetUserPasswordRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
