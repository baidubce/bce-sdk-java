package com.baidubce.services.bes.v3.model.billing;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComponentPrice {
    private BigDecimal price;
    private BigDecimal catalogPrice;
    private BigDecimal discountRate;
}
