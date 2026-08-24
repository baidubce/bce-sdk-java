package com.baidubce.services.bes.v2.model.schedule;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to delete a schedule.
 */
@Data
public class ScheduleDeleteRequest extends AbstractBesV2Request {
    private String clusterId;
    private String scheduleName;
}
