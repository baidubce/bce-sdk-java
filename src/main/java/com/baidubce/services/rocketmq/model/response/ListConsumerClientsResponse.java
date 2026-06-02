package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.ConsumerClient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListConsumerClientsResponse extends RocketMQPagedResponse {
    private List<ConsumerClient> consumerClients;
}
