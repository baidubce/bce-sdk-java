package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.services.kafka.model.PageListRequest;
import lombok.Data;

@Data
public class ListTopicPartitionsRequest extends PageListRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String topicName;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListTopicPartitionsRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListTopicPartitionsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
