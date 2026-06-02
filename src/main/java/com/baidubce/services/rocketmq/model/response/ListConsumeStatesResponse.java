package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.ConsumeState;
import com.baidubce.services.rocketmq.model.ConsumerClient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListConsumeStatesResponse extends RocketMQPagedResponse {
    private List<ConsumeState> consumeStates;
}
