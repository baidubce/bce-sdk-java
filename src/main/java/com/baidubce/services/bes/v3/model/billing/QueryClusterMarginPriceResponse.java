package com.baidubce.services.bes.v3.model.billing;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.Map;

@Data
public class QueryClusterMarginPriceResponse extends AbstractBceResponse {
    private Map<String, ComponentPrice> currentComponentPrices;
    private Map<String, ComponentPrice> updateComponentPrices;
    private Map<String, ComponentPrice> marginComponentPrices;
    private String expirationTime;
}
