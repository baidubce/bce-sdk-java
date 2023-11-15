package com.baidubce.services.kafka.model.consumer;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class ResetConsumerGroupRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String groupName;

    private String topicName;

    private List<Integer> partitions;

    private String resetStrategy;

    private String resetValue;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ResetConsumerGroupRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ResetConsumerGroupRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
