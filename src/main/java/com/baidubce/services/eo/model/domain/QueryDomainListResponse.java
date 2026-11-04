package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoResponse;

import java.util.List;

/**
 * The response of querying the accelerate domain list with simple configuration.
 */
public class QueryDomainListResponse extends EoResponse {
    /**
     * The number of subdomains created under the site. Present when siteId is passed.
     */
    private Integer siteDomainCount;

    /**
     * The subdomain quota of the plan. Present when siteId is passed.
     */
    private Integer planDomainQuota;

    /**
     * The number of subdomains created under the plan. Present when siteId is passed.
     */
    private Integer planDomainCount;

    /**
     * The accelerate domain list.
     */
    private List<Domain> domains;

    /**
     * The total number of returned elements.
     */
    private Integer total;

    /**
     * The page number.
     */
    private Integer pageNo;

    /**
     * The page size.
     */
    private Integer pageSize;

    /**
     * @return siteDomainCount
     */
    public Integer getSiteDomainCount() {
        return siteDomainCount;
    }

    /**
     * @param siteDomainCount
     */
    public void setSiteDomainCount(Integer siteDomainCount) {
        this.siteDomainCount = siteDomainCount;
    }

    /**
     * @return planDomainQuota
     */
    public Integer getPlanDomainQuota() {
        return planDomainQuota;
    }

    /**
     * @param planDomainQuota
     */
    public void setPlanDomainQuota(Integer planDomainQuota) {
        this.planDomainQuota = planDomainQuota;
    }

    /**
     * @return planDomainCount
     */
    public Integer getPlanDomainCount() {
        return planDomainCount;
    }

    /**
     * @param planDomainCount
     */
    public void setPlanDomainCount(Integer planDomainCount) {
        this.planDomainCount = planDomainCount;
    }

    /**
     * @return domains
     */
    public List<Domain> getDomains() {
        return domains;
    }

    /**
     * @param domains
     */
    public void setDomains(List<Domain> domains) {
        this.domains = domains;
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
