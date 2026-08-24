package com.baidubce.services.bes.v3.model.diagnosis;

import lombok.Data;

@Data
public class RiskItem {
    private String id;
    private Integer highRiskCount;
    private Integer lowRiskCount;
}
