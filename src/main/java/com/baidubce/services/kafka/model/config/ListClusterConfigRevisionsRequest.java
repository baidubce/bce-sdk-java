package com.baidubce.services.kafka.model.config;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class ListClusterConfigRevisionsRequest extends AbstractBceRequest {

    private String configId;

    private String state;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListClusterConfigRevisionsRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListClusterConfigRevisionsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
