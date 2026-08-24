package com.baidubce.services.bes.v3.model.billing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillingConfig {

    private String payment;

    private Integer timeLength;

    private String timeUnit;

    private Long expirationTime;

    private Boolean isAutoPay;

}
