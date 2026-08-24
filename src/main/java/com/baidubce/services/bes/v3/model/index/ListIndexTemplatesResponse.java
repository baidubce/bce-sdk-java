package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListIndexTemplatesResponse extends AbstractBceResponse {
    private List<IndexTemplateItem> templates;
    private List<IndexTemplateItem> legacyTemplates;

    @Data
    public static class IndexTemplateItem {
        private String templateName;
        private String indexPatterns;
        private String priority;
        private String order;
    }
}
