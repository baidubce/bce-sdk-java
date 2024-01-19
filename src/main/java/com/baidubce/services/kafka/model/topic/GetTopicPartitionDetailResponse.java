package com.baidubce.services.kafka.model.topic;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class GetTopicPartitionDetailResponse extends AbstractBceResponse {

    private String topicName;
    private TopicPartition partition;
}
