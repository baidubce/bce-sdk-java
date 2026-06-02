package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryMessageResponse extends RocketMQBaseResponse {
    private Message messageDetail;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class QueryMessageInnerResponse extends RocketMQBaseResponse {
        private Message.MessageInner messageDetail;
    }
}
