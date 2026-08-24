package com.baidubce.services.bes.v3.model.action;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListOperationAnalysisDetailsResponse extends AbstractBceResponse {
    private List<KindItem> result;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
}
