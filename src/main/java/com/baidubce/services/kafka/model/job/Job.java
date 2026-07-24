package com.baidubce.services.kafka.model.job;

import lombok.Data;

import java.util.List;

@Data
public class Job {
    private String name ;
    private String actionId;
    private String status;
    private List<Operation> operations;
}
