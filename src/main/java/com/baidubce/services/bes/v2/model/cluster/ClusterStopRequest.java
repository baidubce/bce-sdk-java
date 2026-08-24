package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to stop a cluster.
 */
@Data
public class ClusterStopRequest extends AbstractBesV2Request {
    private String clusterId;
}
