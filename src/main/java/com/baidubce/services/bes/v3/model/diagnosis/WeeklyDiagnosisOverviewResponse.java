package com.baidubce.services.bes.v3.model.diagnosis;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class WeeklyDiagnosisOverviewResponse extends AbstractBceResponse {
    private Object overview;
    private Integer count;
    private List<RiskItem> riskItems;
}
