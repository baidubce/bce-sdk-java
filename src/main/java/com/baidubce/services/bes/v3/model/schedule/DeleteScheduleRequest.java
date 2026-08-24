package com.baidubce.services.bes.v3.model.schedule;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class DeleteScheduleRequest extends AbstractBesV3Request {
    private String clusterId;
    private String scheduleId;
}
