package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for listing auto renew rules, whose result is directly a list.
 */
@Data
public class AutoRenewRuleListResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private List<AutoRenewRule> result;

    @Data
    public static class AutoRenewRule {
        private String uuid;
        private String userId;
        private String clusterId;
        private String region;
        private String renewTimeUnit;
        private Integer renewTime;
        private String createTime;
        private String updateTime;
    }
}
