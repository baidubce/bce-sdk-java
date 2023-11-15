package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of scs slow log
 */
public class ScsSlowLogResponse extends AbstractBceResponse {

    private Integer totalCount;
    private Integer pageNo;
    private Integer pageSize;

    private List<ScsSlowLogItem> result = new ArrayList<ScsSlowLogItem>();

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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

    public List<ScsSlowLogItem> getResult() {
        return result;
    }

    public void setResult(List<ScsSlowLogItem> result) {
        this.result = result;
    }
}
