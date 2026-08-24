package com.baidubce.services.bes.v2.model.schedule;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to list schedules.
 */
@Data
public class ScheduleListRequest extends AbstractBesV2Request {
    private String clusterId;
}
