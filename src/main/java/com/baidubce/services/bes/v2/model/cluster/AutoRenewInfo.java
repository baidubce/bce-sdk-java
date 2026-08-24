package com.baidubce.services.bes.v2.model.cluster;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Auto-renew configuration for cluster creation.
 */
@Data
public class AutoRenewInfo {
    private String renewTimeUnit;
    private BigDecimal renewTime;
}
