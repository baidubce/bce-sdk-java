package com.baidubce.services.kafka.model.topic;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class CreateTopicResponse extends AbstractBceResponse {

    private String topicName;
}
