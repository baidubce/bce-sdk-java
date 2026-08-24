package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to check whether an inspection task can be submitted.
 */
@Data
public class InspectCheckBusyRequest extends AbstractBesV2Request {
    private String clusterId;
}
