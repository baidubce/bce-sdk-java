package com.baidubce.services.aihc.model.inference;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PubAccessRequest extends AbstractBceRequest {

    private String appId;
    private boolean publicAccess;
    private String eip;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return PubAccessRequest with credentials.
     */
    public PubAccessRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
