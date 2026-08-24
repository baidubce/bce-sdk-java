package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to start an instance.
 */
@Data
public class InstanceStartRequest extends AbstractBesV2Request {
    private String clusterId;
    private String instanceId;
}
