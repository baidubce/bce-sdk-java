package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to view scale-in instance candidates.
 */
@Data
public class ScaleInInstancesRequest extends AbstractBesV2Request {
    private String clusterId;
    private String moduleType;
}
