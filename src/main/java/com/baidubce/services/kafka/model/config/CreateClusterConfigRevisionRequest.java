package com.baidubce.services.kafka.model.config;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.Map;

@Data
public class CreateClusterConfigRevisionRequest extends AbstractBceRequest {

    /**
     * The id of config.
     */
    private String configId;

    private Integer revisionId;

    private String description;

    private Map<String, String> context;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public CreateClusterConfigRevisionRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public CreateClusterConfigRevisionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
