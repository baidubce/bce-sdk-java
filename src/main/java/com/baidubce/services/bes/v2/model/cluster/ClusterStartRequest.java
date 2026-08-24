package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to start a cluster.
 */
@Data
public class ClusterStartRequest extends AbstractBesV2Request {
    private String clusterId;
}
