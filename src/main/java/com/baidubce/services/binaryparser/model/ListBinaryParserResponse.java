package com.baidubce.services.binaryparser.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by yuanyoujun on 2017/9/2.
 */
public class ListBinaryParserResponse extends AbstractBceResponse {
    private List<BinaryParser> result;
    private int totalCount;
    private int pageNo;
    private int pageSize;


    public List<BinaryParser> getResult() {
        return result;
    }

    public void setResult(List<BinaryParser> result) {
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
