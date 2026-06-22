package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Paged response of security groups grouped by vpc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsPageSecurityResponse extends AbstractBceResponse {

    private String orderBy = "";

    private String order = "";

    private int pageNo = 1;

    private int pageSize = 0;

    private List<LinkedHashMap> result = new ArrayList<LinkedHashMap>();

    private int totalCount = 0;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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

    public List<LinkedHashMap> getResult() {
        return result;
    }

    public void setResult(List<LinkedHashMap> result) {
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
