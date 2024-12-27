package com.baidubce.services.kafka.model.quota;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class ListQuotasRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String entityType;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListQuotasRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListQuotasRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
