package com.baidubce.services.bes.v3.model.billing;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryNodeTypePrice {
    private String type;
    private BigDecimal nodePrice;
    private BigDecimal nodeCatalogPrice;
    private BigDecimal diskPrice;
    private BigDecimal diskCatalogPrice;
}
