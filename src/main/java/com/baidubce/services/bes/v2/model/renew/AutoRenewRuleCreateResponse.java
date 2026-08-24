package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

/**
 * Response for creating an auto renew rule.
 */
@Data
public class AutoRenewRuleCreateResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    @JsonDeserialize(using = AutoRenewRuleCreateResultDeserializer.class)
    private Result result;

    @Data
    public static class Result {
        private String orderId;
    }
}
