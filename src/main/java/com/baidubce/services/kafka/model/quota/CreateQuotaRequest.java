package com.baidubce.services.kafka.model.quota;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class CreateQuotaRequest extends AbstractBceRequest {

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
     * @return public CreateQuotaRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public CreateQuotaRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
