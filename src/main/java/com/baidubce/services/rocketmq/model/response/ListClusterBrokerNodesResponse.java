package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.BrokerNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListClusterBrokerNodesResponse extends RocketMQPagedResponse {
    private List<BrokerNode> brokerNodes;
}
