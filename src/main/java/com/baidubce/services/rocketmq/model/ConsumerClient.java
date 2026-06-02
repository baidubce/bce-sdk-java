package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class ConsumerClient {
    private String clientId;
    private String clientAddr;
    private String language;
    private Integer version;
}
