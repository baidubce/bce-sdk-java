package com.baidubce.services.rocketmq.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RocketMQPagedResponse extends RocketMQBaseResponse {
    private String marker;

    private Boolean isTruncated;

    private String nextMarker;

    private Integer maxKeys;
}
