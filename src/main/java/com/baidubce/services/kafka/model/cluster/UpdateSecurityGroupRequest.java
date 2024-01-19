package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class UpdateSecurityGroupRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private List<String> securityGroupIds;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public UpdateSecurityGroupRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public UpdateSecurityGroupRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
