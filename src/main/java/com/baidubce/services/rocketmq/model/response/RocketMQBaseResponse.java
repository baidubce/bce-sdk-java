package com.baidubce.services.rocketmq.model.response;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RocketMQBaseResponse extends AbstractBceResponse {
    private String requestId;

    private String code;

    private String message;
}
