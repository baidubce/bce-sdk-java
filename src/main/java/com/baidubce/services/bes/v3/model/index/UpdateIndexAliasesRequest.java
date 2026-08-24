package com.baidubce.services.bes.v3.model.index;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;
import lombok.Data;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

@Data
public class UpdateIndexAliasesRequest extends AbstractBesV3Request {
    private String clusterId;
    private String indexName;
    private List<AliasAction> actions;

    @Data
    public static class AliasAction {
        @NotNull(message = "action should not be null.")
        private String action;
        @NotNull(message = "alias should not be null.")
        private String alias;
        private Boolean isWriteIndex;
        private String indexRouting;
        private String searchRouting;
        private Map<String, Object> filter;
    }
}
