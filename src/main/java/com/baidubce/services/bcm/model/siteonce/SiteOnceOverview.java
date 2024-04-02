package com.baidubce.services.bcm.model.siteonce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteOnceOverview {
    private BigDecimal success;
    private Map<String, Double> metrics;
    private int sumSampleNum;
    private int rightSampleNum;
    private int errSampleNum;
}
