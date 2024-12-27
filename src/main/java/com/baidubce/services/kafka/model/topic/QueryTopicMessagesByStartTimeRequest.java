package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class QueryTopicMessagesByStartTimeRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String topicName;

    private Integer partitionId;

    private long startTime;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public QueryTopicMessagesByStartTimeRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public QueryTopicMessagesByStartTimeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
