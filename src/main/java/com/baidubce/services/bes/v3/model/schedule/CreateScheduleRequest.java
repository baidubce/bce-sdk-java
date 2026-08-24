package com.baidubce.services.bes.v3.model.schedule;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.Map;

@Data
public class CreateScheduleRequest extends AbstractBesV3Request {
    private String clusterId;
    private String scheduleName;
    private String scheduleType;
    private String cronExpr;
    private Map<String, Object> params;
}
