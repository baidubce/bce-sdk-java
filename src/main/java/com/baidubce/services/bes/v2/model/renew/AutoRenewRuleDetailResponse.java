package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Response for the auto renew rule detail.
 */
@Data
public class AutoRenewRuleDetailResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String clusterId;
        private String renewTimeUnit;
        private BigDecimal renewTime;
        private BigDecimal expireTime;
        private BigDecimal nextRenewTime;
    }
}
