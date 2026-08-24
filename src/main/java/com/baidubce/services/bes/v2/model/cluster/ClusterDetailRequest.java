package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to view cluster detail.
 */
@Data
public class ClusterDetailRequest extends AbstractBesV2Request {
    private String clusterId;
}
