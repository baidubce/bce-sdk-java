package com.baidubce.services.bes.v3.model.diagnosis;

import lombok.Data;

@Data
public class DiagnosisReportResultItem {
    private String id;
    private String type;
    private String conclusion;
    private Boolean hasLowRiskAdvice;
    private Boolean notExistIndex;
}
