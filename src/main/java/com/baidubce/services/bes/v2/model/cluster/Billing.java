package com.baidubce.services.bes.v2.model.cluster;

import lombok.Data;

/**
 * Billing configuration.
 */
@Data
public class Billing {
    private String paymentType;
    private Integer time;
    private Boolean enableAutoRenew;
    private AutoRenewInfo autoRenewInfo;
    private String coupon;
}
