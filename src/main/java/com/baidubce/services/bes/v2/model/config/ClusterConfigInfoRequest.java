package com.baidubce.services.bes.v2.model.config;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get cluster config.
 */
@Data
public class ClusterConfigInfoRequest extends AbstractBesV2Request {
    private String clusterId;
}
