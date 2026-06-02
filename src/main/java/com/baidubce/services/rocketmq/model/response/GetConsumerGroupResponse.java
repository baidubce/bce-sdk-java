package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.ConsumerGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetConsumerGroupResponse extends RocketMQBaseResponse {
    private ConsumerGroup consumerGroup;
}
