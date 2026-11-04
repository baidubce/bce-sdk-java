package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoResponse;

import java.util.List;

/**
 * The response of querying load balances of a site.
 */
public class QueryLoadBalanceResponse extends EoResponse {
    /**
     * The total number of load balances.
     */
    private Integer total;

    /**
     * The load balance details.
     */
    private List<LoadBalanceDetail> loadBalanceDetail;

    /**
     * The page number, present when paged query.
     */
    private Integer pageNo;

    /**
     * The page size, present when paged query.
     */
    private Integer pageSize;

    /**
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return loadBalanceDetail
     */
    public List<LoadBalanceDetail> getLoadBalanceDetail() {
        return loadBalanceDetail;
    }

    /**
     * @param loadBalanceDetail
     */
    public void setLoadBalanceDetail(List<LoadBalanceDetail> loadBalanceDetail) {
        this.loadBalanceDetail = loadBalanceDetail;
    }

    /**
     * @return pageNo
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
