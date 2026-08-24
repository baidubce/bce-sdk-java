package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Response for deleting an auto renew rule.
 */
@Data
public class AutoRenewRuleDeleteResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private BigDecimal expireTime;
    }
}
