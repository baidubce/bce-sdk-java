package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoResponse;

import java.util.List;

/**
 * The response of querying origin pools of a site.
 */
public class QueryOriginPoolResponse extends EoResponse {
    /**
     * The origin pool list.
     */
    private List<OriginPoolInfo> originPools;

    /**
     * The page number, defaults to 1.
     */
    private Integer pageNo;

    /**
     * The page size, defaults to 10.
     */
    private Integer pageSize;

    /**
     * The total number of origin pools.
     */
    private Integer total;

    /**
     * @return originPools
     */
    public List<OriginPoolInfo> getOriginPools() {
        return originPools;
    }

    /**
     * @param originPools
     */
    public void setOriginPools(List<OriginPoolInfo> originPools) {
        this.originPools = originPools;
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
}
