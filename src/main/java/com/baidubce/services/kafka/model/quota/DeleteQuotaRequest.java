package com.baidubce.services.kafka.model.quota;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class DeleteQuotaRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String username;

    private Boolean userDefault;

    private String clientId;

    private Boolean clientDefault;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public DeleteQuotaRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public DeleteQuotaRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
