package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Response for listing available coupons.
 */
@Data
public class CouponListResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private List<Coupon> coupons;
    }

    @Data
    public static class Coupon {
        private BigDecimal id;
        private String name;
        private String couponType;
        private String productType;
        private List<String> productTypes;
        private String productRuleDescription;
        private BigDecimal totalAmount;
        private String balance;
        private String amountOrDiscount;
        private String usedAmount;
        private BigDecimal beginTime;
        private BigDecimal endTime;
        private String couponStatus;
        private String status;
        private String region;
        private Map<String, Object> conditionArgsMap;
        private Map<String, Object> effectArgsMap;
        private String conditionEffectDescription;
    }
}
