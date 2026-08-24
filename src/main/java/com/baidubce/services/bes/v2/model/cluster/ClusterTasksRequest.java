package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get cluster operation history.
 */
@Data
public class ClusterTasksRequest extends AbstractBesV2Request {
    private String clusterId;
}
