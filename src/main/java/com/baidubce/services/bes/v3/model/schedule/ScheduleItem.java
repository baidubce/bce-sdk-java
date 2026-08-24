package com.baidubce.services.bes.v3.model.schedule;

import lombok.Data;

import java.util.Map;

@Data
public class ScheduleItem {
    private String scheduleId;
    private String scheduleName;
    private String scheduleType;
    private String cronExpr;
    private Boolean enabled;
    private String createTime;
    private String lastTriggerTime;
    private String lastStatus;
    private String lastErrorMessage;
    private Integer executedCount;
    private Integer failedCount;
    private Map<String, Object> params;
}
