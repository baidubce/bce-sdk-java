package com.baidubce.services.rocketmq.model;

import lombok.Data;

@Data
public class Operation {
    private String actionId;
    private String name;
    private String operationId;
    private String  type;
    private String state;
    private String status;
    private Integer process;
    private String schedule;
    private Integer operationNo;
    private Long createTime;
    private Long startTime;
    private Long endTime;
}
