package com.baidubce.services.kafka.model.job;

import lombok.Data;

import java.util.List;

@Data
public class OperationDetail {

    private String jobId;

    private String operationId;

    private String type;

    private String state;

    private int process;

    private List<JobGroup> groups;

    private String sourceContext;

    private String targetContext;

    private String createTime;

    private String startTime;

    private String endTime;
}
