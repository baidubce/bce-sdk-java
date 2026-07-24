package com.baidubce.services.kafka.model.cluster;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class MigrateClusterAzRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    @JsonIgnore
    private String clusterId;

    private Integer resizeType;

    private List<String> couponIds;

    private Boolean isAutoPay;

    private List<String> logicalZones;

    private List<String> subnets;

    private Integer numberOfBrokerNodes;

    private Integer batchSize;

    private Long interBrokerThrottle;

    @Override
    public MigrateClusterAzRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
