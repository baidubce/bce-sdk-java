package com.baidubce.services.kafka.model.config;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;
import lombok.Data;

@Data
public class ListClusterConfigsRequest extends ListRequest {

    private String configName;

    private String state;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListClusterConfigsRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListClusterConfigsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
