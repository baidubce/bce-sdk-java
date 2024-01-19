package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class IncreaseBrokerCountRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private Integer numberOfBrokerNodes;

    private List<String> couponIds;

    private Boolean isAutoPay;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public IncreaseBrokerCountRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public IncreaseBrokerCountRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
