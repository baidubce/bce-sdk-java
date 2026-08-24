package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to unbind EIP from a cluster.
 */
@Data
public class ClusterEipUnbindRequest extends AbstractBesV2Request {
    private String deployId;
    private String moduleTemplateName;
}
