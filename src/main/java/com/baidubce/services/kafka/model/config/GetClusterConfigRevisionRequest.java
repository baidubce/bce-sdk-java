package com.baidubce.services.kafka.model.config;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class GetClusterConfigRevisionRequest extends AbstractBceRequest {
    /**
     * The id of config.
     */
    private String configId;

    /**
     * The revision id of config.
     */
    private Integer revisionId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public GetClusterConfigRevisionRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public GetClusterConfigRevisionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
