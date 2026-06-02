package com.baidubce.services.rocketmq.model.response;

import com.baidubce.services.rocketmq.model.MessageQueue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SendMessageResponse extends RocketMQBaseResponse {
    private String sendStatus;
    private String msgId;
    private MessageQueue messageQueue;
    private Long queueOffset;
    private String offsetMsgId;
}
