package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to bind EIP to a cluster.
 */
@Data
public class ClusterEipBindRequest extends AbstractBesV2Request {
    private String instanceId;
    private String instanceType;
    private String eip;
}
