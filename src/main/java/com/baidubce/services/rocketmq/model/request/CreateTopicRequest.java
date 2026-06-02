package com.baidubce.services.rocketmq.model.request;

import com.baidubce.services.rocketmq.model.TopicConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTopicRequest extends RocketMQBaseRequest {
    private String clusterId;
    private String topicName;
    private List<TopicConfig> topicConfigs;
    private Integer permission;
}
