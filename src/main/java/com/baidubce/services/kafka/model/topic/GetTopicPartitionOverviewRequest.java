package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class GetTopicPartitionOverviewRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String topicName;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public GetTopicPartitionOverviewRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public GetTopicPartitionOverviewRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
