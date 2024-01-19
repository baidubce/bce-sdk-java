package com.baidubce.services.kafka.model.config;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.Map;

@Data
public class CreateClusterConfigRequest extends AbstractBceRequest {

    private String name;

    private String description;

    private Map<String, String> context;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public CreateClusterConfigRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public CreateClusterConfigRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }


}
