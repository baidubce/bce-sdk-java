package com.baidubce.services.bes.v3.model.diagnosis;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class LatestDiagnosisOverviewResponse extends AbstractBceResponse {
    private String reportId;
    private String status;
    private String execTime;
    private String countSafe;
    private String countLowRisk;
    private String countHighRisk;
    private String countError;
}
