package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class UpdateAccessConfigRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private Boolean aclEnabled;

    private List<Authentication> authentications;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public UpdateAccessConfigRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public UpdateAccessConfigRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
