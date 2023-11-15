package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.Map;

@Data
public class CreateTopicRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String topicName;

    private int partitionNum;

    private int replicationFactor;

    private Map<String, String> otherConfigs;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public CreateTopicRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public CreateTopicRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
