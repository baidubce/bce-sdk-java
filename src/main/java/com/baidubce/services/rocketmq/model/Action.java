package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class Action {
    private String name;
    private String actionId;
    private String status;
    private Long createTime;
    private Long endTime;
}
