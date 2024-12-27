package com.baidubce.services.aihc.model.inference;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppDetailsRequest extends AbstractBceRequest {

    private String appId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return AppDetailsRequest with credentials.
     */
    public AppDetailsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
