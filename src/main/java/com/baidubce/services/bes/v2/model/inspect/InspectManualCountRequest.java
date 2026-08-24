package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get the count of manual inspections completed today.
 */
@Data
public class InspectManualCountRequest extends AbstractBesV2Request {
    private String clusterId;
}
