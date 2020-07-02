package com.baidubce.services.bmr.model;

import java.util.List;

public class ScheduleHistoryModelsVo {
    private List<ScheduleHistoryModelVo> models;
    private Integer pageNo;
    private Integer pageSize;
    private Integer total;

    public List<ScheduleHistoryModelVo> getModels() {
        return models;
    }

    public void setModels(List<ScheduleHistoryModelVo> models) {
        this.models = models;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
