package com.baidubce.services.kafka.model.config;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class DeleteClusterConfigRequest extends AbstractBceRequest {

    /**
     * The id of config.
     */
    private String configId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public DeleteClusterConfigRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public DeleteClusterConfigRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
