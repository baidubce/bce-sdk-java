package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;
import lombok.Data;

@Data
public class ListClustersRequest extends ListRequest {

    private String clusterName;

    private String state;

    private String mode;

    private String kafkaVersion;

    private String payment;

    private String tagKey;

    private String tagValue;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListClustersRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListClustersRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
