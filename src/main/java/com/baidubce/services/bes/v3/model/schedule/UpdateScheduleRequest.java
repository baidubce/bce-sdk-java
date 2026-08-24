package com.baidubce.services.bes.v3.model.schedule;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateScheduleRequest extends AbstractBesV3Request {
    private String clusterId;
    private String scheduleId;
    private String cronExpr;
    private Map<String, Object> params;
}
