package com.baidubce.services.bmr.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

public class SchedulePlanDetailListResponse extends AbstractBceResponse {
    private List<SchedulePlanSummaryModelVo> listSchedulePlan;
    private Integer pageNo;
    private Integer pageSize;

    public List<SchedulePlanSummaryModelVo> getListSchedulePlan() {
        return listSchedulePlan;
    }

    public void setListSchedulePlan(List<SchedulePlanSummaryModelVo> listSchedulePlan) {
        this.listSchedulePlan = listSchedulePlan;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
