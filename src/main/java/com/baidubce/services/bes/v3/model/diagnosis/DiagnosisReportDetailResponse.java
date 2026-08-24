package com.baidubce.services.bes.v3.model.diagnosis;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class DiagnosisReportDetailResponse extends AbstractBceResponse {
    private String state;
    private String type;
    private String indices;
    private Integer totalCount;
    private String execTime;
    private List<DiagnosisReportGroupItem> groups;
}
