package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

public class GetPackageUsageListResponse extends CdnResponse {
    private long totalCount;

    private int pageNo = 1;

    /**
     * 最大支持3000
     */
    private int pageSize = 100;

    private String orderBy;

    private String order;

    private List<PackageDetail> result;

    public GetPackageUsageListResponse() {
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<PackageDetail> getResult() {
        return result;
    }

    public void setResult(List<PackageDetail> result) {
        this.result = result;
    }
}
