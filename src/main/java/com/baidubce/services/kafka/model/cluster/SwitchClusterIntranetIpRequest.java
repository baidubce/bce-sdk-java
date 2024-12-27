package com.baidubce.services.kafka.model.cluster;

import java.util.Set;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class SwitchClusterIntranetIpRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private Boolean intranetIpEnabled;

    private Set<AuthMode> authenticationMode;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public SwitchClusterIntranetIpRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public SwitchClusterIntranetIpRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
