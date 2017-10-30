package com.baidubce.services.binaryparser.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2017/9/2.
 */
public class ListBinaryParserRequest extends GenericAccountRequest {
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
