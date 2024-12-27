package com.baidubce.services.bcm.model.alarmhouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class Alarm {
    private String id;
    private String seriesId;
    private String userId;
    private String initState;
    private String state;
    private String closeReason;
    private Long startTime;
    private Long endTime;

    private Resource resource;
    private Policy policy;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Action> actions;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AlertMetric> alertMetrics;


}
