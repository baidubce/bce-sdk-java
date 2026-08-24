package com.baidubce.services.bes.v3.model.diagnosis;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListDiagnosisReportsResponse extends AbstractBceResponse {
    private List<DiagnosisReportListItem> reports;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
}
