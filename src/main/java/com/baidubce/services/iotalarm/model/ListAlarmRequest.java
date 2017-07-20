package com.baidubce.services.iotalarm.model;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yuanyoujun on 2017/6/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListAlarmRequest extends GenericAccountRequest {
    private int pageNo = 1;
    private int pageSize = 50;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
