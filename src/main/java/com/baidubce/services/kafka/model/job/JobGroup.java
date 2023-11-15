package com.baidubce.services.kafka.model.job;

import lombok.Data;

@Data
public class JobGroup {

    private String groupName;

    private JobState state;
}
