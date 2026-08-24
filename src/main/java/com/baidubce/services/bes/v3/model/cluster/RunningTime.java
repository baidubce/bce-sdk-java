package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

@Data
public class RunningTime {
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer second;
    private Long runningTimeMs;

}
