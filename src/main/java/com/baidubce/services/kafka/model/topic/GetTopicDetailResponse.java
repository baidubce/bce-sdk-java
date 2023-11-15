package com.baidubce.services.kafka.model.topic;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class GetTopicDetailResponse extends AbstractBceResponse {

    private TopicDetail topic;
}
