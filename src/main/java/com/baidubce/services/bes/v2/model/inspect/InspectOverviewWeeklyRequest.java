package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get the weekly inspection overview.
 */
@Data
public class InspectOverviewWeeklyRequest extends AbstractBesV2Request {
    private String clusterId;
}
