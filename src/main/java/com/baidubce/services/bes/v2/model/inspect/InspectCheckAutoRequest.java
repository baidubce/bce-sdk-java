package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to check whether auto inspection is enabled.
 */
@Data
public class InspectCheckAutoRequest extends AbstractBesV2Request {
    private String clusterId;
}
