package com.baidubce.services.kafka.model.topic;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateTopicRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String topicName;

    private String partitionNum;

    private Map<String, String> otherConfigs;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public UpdateTopicRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public UpdateTopicRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public void setPartitionNum(int partitionNum) {
        this.partitionNum = String.valueOf(partitionNum);
    }
}
