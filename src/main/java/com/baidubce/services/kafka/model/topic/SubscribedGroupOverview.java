package com.baidubce.services.kafka.model.topic;

import lombok.Data;

@Data
public class SubscribedGroupOverview {

    private Integer subscribedGroupNum;

    private String lastConsumeTime;
}
