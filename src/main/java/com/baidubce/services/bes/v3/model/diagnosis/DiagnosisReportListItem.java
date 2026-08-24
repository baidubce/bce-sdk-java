package com.baidubce.services.bes.v3.model.diagnosis;

import lombok.Data;

@Data
public class DiagnosisReportListItem {
    private String reportId;
    private String type;
    private String execTime;
    private Integer lowRiskCount;
    private Integer highRiskCount;
    private Integer errorCount;
}
