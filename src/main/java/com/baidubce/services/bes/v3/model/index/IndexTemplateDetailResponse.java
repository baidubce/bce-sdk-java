package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.Map;

@Data
public class IndexTemplateDetailResponse extends AbstractBceResponse {
    private String templateName;
    private String isLegacy;
    private String type;
    private String indexPatterns;
    private String priority;
    private String order;
    private Map<String, Object> template;
}
