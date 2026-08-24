package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to list clusters.
 */
@Data
public class ClusterListRequest extends AbstractBesV2Request {
    private Integer pageNo;
    private Integer pageSize;
}
