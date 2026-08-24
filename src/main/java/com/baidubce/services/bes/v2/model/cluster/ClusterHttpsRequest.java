package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to enable or disable HTTPS for a cluster.
 */
@Data
public class ClusterHttpsRequest extends AbstractBesV2Request {
    private String clusterId;
    private Boolean enableSSL;
}
