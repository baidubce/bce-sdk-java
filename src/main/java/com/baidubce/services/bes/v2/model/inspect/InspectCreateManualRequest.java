package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to create a manual inspection task.
 */
@Data
public class InspectCreateManualRequest extends AbstractBesV2Request {
    private String clusterId;
}
