package com.baidubce.services.bes.v3.model.billing;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class QueryConfigPriceResponse extends AbstractBceResponse {
    private BigDecimal price;
    private BigDecimal catalogPrice;
    private List<QueryNodeTypePrice> nodeTypePrices;
}
