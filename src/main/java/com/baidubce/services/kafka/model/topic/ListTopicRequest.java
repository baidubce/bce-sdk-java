package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class ListTopicRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    @JsonIgnore
    private String topicName;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListTopicRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListTopicRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
