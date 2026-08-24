package com.baidubce.services.bes.v2.model.config;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.Map;

/**
 * Request to update cluster config.
 */
@Data
public class ClusterConfigUpdateRequest extends AbstractBesV2Request {
    private String clusterId;
    private Map<String, Object> esConfigsMap;
    private Map<String, Object> kibanaConfigsMap;
}
