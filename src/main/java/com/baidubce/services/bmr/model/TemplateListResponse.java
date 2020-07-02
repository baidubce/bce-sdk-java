package com.baidubce.services.bmr.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

public class TemplateListResponse extends AbstractBceResponse {
    private List<ClusterTemplateSummaryVo> result;

    public List<ClusterTemplateSummaryVo> getResult() {
        return result;
    }

    public void setResult(List<ClusterTemplateSummaryVo> result) {
        this.result = result;
    }
}
