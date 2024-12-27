package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class SendTopicMessageRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String topicName;

    private Integer partitionId;

    private String key;

    private String value;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public SendTopicMessageRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public SendTopicMessageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
