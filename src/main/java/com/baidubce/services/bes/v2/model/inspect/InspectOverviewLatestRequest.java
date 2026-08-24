package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get the latest inspection overview.
 */
@Data
public class InspectOverviewLatestRequest extends AbstractBesV2Request {
    private String clusterId;
}
