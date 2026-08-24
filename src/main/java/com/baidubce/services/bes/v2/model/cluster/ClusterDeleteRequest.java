package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to delete a cluster.
 */
@Data
public class ClusterDeleteRequest extends AbstractBesV2Request {
    private String clusterId;
    private String refundReason;
}
