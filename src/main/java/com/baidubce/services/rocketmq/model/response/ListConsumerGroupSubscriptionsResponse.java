package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.Cluster;
import com.baidubce.services.rocketmq.model.SubscriptionRelationship;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListConsumerGroupSubscriptionsResponse extends RocketMQPagedResponse {
    private List<SubscriptionRelationship> subscriptionRelationships;
}
