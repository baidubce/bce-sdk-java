package com.baidubce.services.kafka.model.quota;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class UpdateQuotaRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String username;

    private Boolean userDefault;

    private String clientId;

    private Boolean clientDefault;

    private Long producerByteRate;

    private Long consumerByteRate;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public UpdateQuotaRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public UpdateQuotaRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
