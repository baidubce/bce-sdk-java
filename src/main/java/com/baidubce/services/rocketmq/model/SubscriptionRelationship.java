package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class SubscriptionRelationship {
    private String topicName;
    private String subExpressionType;
    private String subExpression;
}
