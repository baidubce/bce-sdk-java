package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class IndexFieldTypesResult extends AbstractBceResponse {
    private List<FieldTypeItem> fieldTypes;

    @Data
    public static class FieldTypeItem {
        private String typeName;
        private String isVector;
        private String isAnalyzable;
        private String isAggregatable;
    }
}
