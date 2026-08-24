package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to restart a cluster.
 */
@Data
public class ClusterRestartRequest extends AbstractBesV2Request {
    private String clusterId;
    private String mode;
}
