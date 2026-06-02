package com.baidubce.services.rocketmq.model;

import lombok.Data;

import java.util.List;

@Data
public class ConsumerGroup {
    private String groupName;
    private List<String> brokerNames;
    private boolean consumeBroadcastEnable;
    private int retryMaxTimes;
    private String messageModel;
    private boolean consumeEnable;
}
