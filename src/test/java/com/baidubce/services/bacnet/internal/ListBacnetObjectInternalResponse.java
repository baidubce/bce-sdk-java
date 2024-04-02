package com.baidubce.services.bacnet.internal;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListBacnetObjectInternalResponse extends AbstractBceResponse {
    private List<BacnetObjectInternal> result;
    private int totalCount;
    private int pageNo;
    private int pageSize;

    public List<BacnetObjectInternal> getResult() {
        return result;
    }

    public void setResult(List<BacnetObjectInternal> result) {
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

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
