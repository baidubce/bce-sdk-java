package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.Topic;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetTopicResponse extends RocketMQBaseResponse {
    private Topic topic;
}
