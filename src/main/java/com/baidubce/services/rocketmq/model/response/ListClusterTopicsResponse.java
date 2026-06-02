package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.Topic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListClusterTopicsResponse extends RocketMQPagedResponse {
    private List<Topic> topics;
}
