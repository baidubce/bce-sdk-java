package com.baidubce.services.bes.v3.model.diagnosis;

import lombok.Data;

import java.util.List;

@Data
public class DiagnosisReportGroupItem {
    private String grade;
    private Integer itemCount;
    private List<DiagnosisReportResultItem> results;
}
