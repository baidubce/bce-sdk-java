package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Data;

@Data
public class ListTopicConfigOptionsRequest extends AbstractBceRequest {

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListTopicConfigOptionsRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListTopicConfigOptionsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
