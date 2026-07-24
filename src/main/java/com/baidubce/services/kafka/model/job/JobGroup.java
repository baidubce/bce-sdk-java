package com.baidubce.services.kafka.model.job;

import lombok.Data;

@Data
public class JobGroup {

    private String groupName;

    private String groupNameCN;

    private String state;

    private boolean analysis;
}
