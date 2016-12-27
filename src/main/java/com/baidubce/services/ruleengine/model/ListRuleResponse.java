package com.baidubce.services.ruleengine.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Created by yuanyoujun on 2016/10/9.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListRuleResponse extends AbstractBceResponse {
    private List<Rule> result;
    private int totalCount;
    private int pageNo;
    private int pageSize;

    public List<Rule> getResult() {
        return result;
    }

    public void setResult(List<Rule> result) {
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
