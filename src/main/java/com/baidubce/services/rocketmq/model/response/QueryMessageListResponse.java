package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.Message;
import com.baidubce.services.rocketmq.model.TopicQueue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryMessageListResponse extends RocketMQPagedResponse {
    private List<Message> messages;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class QueryMessageListInnerResponse extends RocketMQPagedResponse {
        private List<Message.MessageInner> messages;
    }
}
