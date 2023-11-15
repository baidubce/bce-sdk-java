package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;
import lombok.Data;

@Data
public class GetClusterNodesRequest extends ListRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String state;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public GetClusterNodesRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public GetClusterNodesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
