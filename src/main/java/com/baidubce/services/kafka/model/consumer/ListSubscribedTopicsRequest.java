package com.baidubce.services.kafka.model.consumer;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class ListSubscribedTopicsRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String groupName;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListSubscribedTopicsRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListSubscribedTopicsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
