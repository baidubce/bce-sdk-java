package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListIsmPoliciesResult extends AbstractBceResponse {
    private List<IsmPolicyItem> policies;

    @Data
    public static class IsmPolicyItem {
        private String policyId;
        private String description;
        private String lastUpdatedTime;
    }
}
