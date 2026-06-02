package com.baidubce.services.rocketmq.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SendMessageRequest extends RocketMQBaseRequest {
    private String clusterId;
    private String topicName;
    private String key;
    private String tag;
    private byte[] body;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SendMessageInnerRequest extends RocketMQBaseRequest {
        private String topicName;
        private String key;
        private String tag;
        private String base64Body;
    }
}
