package com.baidubce.services.bes.v3.model.index;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;
import lombok.Data;

import java.util.List;

@Data
public class CreateIndexTemplateRequest extends AbstractBesV3Request {
    private String clusterId;
    private String templateName;
    private List<String> indexPatterns;
    private String priority;
    private String order;
    private String isLegacy;
    private Object template;
}
