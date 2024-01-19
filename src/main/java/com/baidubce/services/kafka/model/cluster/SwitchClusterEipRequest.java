package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SwitchClusterEipRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private Boolean publicIpEnabled;

    private Integer publicIpBandwidth;

    private Boolean aclEnabled;

    private Set<AuthMode> authenticationMode;

    private List<String> couponIds;

    private Boolean isAutoPay;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public SwitchClusterEipRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public SwitchClusterEipRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
