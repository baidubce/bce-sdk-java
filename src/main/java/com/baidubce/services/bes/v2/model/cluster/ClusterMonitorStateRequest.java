package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to enable or disable Grafana monitor for a cluster.
 */
@Data
public class ClusterMonitorStateRequest extends AbstractBesV2Request {
    private String clusterId;
    private Boolean enableMonitor;
}
