package com.baidubce.services.ruleengine.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2016/10/9.
 */
public class ListRuleRequest extends GenericAccountRequest {
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
