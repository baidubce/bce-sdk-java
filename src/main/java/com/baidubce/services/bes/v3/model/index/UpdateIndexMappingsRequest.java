package com.baidubce.services.bes.v3.model.index;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateIndexMappingsRequest extends AbstractBesV3Request {
    private String clusterId;
    private String indexName;
    private String type;
    private Map<String, Object> mappings;
}
