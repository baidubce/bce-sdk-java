package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class GetTopicPartitionDetailRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String topicName;

    private String partitionId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public GetTopicPartitionDetailRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public GetTopicPartitionDetailRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public void setPartitionId(int partitionId) {
        this.partitionId = String.valueOf(partitionId);
    }
}
