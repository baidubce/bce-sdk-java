package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get the manual inspection task config.
 */
@Data
public class InspectManualConfigGetRequest extends AbstractBesV2Request {
    private String clusterId;
}
