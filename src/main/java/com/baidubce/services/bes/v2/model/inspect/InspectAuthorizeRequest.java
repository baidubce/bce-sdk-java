package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to authorize cluster inspection.
 */
@Data
public class InspectAuthorizeRequest extends AbstractBesV2Request {
    private String clusterId;
}
