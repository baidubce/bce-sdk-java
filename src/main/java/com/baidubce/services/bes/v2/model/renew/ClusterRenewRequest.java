package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to renew a cluster.
 */
@Data
public class ClusterRenewRequest extends AbstractBesV2Request {
    private String clusterId;
    private Integer time;
}
