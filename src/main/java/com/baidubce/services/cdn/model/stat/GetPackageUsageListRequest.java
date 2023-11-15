package com.baidubce.services.cdn.model.stat;

import com.baidubce.services.cdn.model.CdnRequest;

public class GetPackageUsageListRequest extends CdnRequest {

    private int pageNo = 1;

    /**
     * 最大支持3000
     */
    private int pageSize = 100;

    /**
     * 使用中:RUNNING; 已创建:CREATED; 已过期:EXPIRED; 初始化，待创建:INITIAL; 已用完:USED_UP; 已销毁:DESTROYED;
     */
    private String status;

    private String orderBy;

    private String order;

    public GetPackageUsageListRequest() {
    }

    public GetPackageUsageListRequest(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
