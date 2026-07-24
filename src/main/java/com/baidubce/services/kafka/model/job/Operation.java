package com.baidubce.services.kafka.model.job;

import lombok.Data;

@Data
public class Operation {
    private String actionId;
    private String name;
    private String status;
    private String operationId;
    private String type;
    private String state;
    private Integer process;
    private String schedule;
    private String createTime;
    private String startTime;
    private String endTime;
    private Boolean started;
}
