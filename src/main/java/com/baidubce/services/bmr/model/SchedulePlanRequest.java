package com.baidubce.services.bmr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class SchedulePlanRequest extends AbstractBceRequest {
    private String schedulePlanId;

    private Integer pageNo;

    private Integer pageSize;

    public SchedulePlanRequest withSchedulePlanId(String schedulePlanId) {
        this.setSchedulePlanId(schedulePlanId);
        return this;
    }

    public SchedulePlanRequest withPageNo(int pageNo) {
        this.setPageNo(pageNo);
        return this;
    }

    public SchedulePlanRequest withPageSize(int pageSize) {
        this.setPageSize(pageSize);
        return this;
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

    public String getSchedulePlanId() {
        return schedulePlanId;
    }

    public void setSchedulePlanId(String schedulePlanId) {
        this.schedulePlanId = schedulePlanId;
    }

    @Override public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
