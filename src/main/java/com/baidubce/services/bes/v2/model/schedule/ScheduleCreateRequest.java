package com.baidubce.services.bes.v2.model.schedule;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.Map;

/**
 * Request to create a schedule.
 */
@Data
public class ScheduleCreateRequest extends AbstractBesV2Request {
    private String clusterId;
    private String schedule;
    private String scheduleName;
    private String taskType;
    private Map<String, Object> task;
}
