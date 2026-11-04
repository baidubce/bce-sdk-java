package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for querying load balances of a site.
 */
public class QueryLoadBalanceRequest extends EoRequest {
    /**
     * The site id that the load balances belong to.
     */
    private Integer siteId;

    /**
     * The load balance name filter.
     */
    private String name;

    /**
     * The load balance id filter.
     */
    private String id;

    /**
     * Whether to query all results. True for full query, false or omitted for paged query.
     */
    private Boolean fetchAll;

    /**
     * The page number, defaults to 1.
     */
    private Integer pageNo;

    /**
     * The page size, defaults to 10.
     */
    private Integer pageSize;

    /**
     * @param siteId
     * @return this object
     */
    public QueryLoadBalanceRequest withSiteId(Integer siteId) {
        this.siteId = siteId;
        return this;
    }

    /**
     * @return siteId
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * @param siteId
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * @param name
     * @return this object
     */
    public QueryLoadBalanceRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param id
     * @return this object
     */
    public QueryLoadBalanceRequest withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param fetchAll
     * @return this object
     */
    public QueryLoadBalanceRequest withFetchAll(Boolean fetchAll) {
        this.fetchAll = fetchAll;
        return this;
    }

    /**
     * @return fetchAll
     */
    public Boolean getFetchAll() {
        return fetchAll;
    }

    /**
     * @param fetchAll
     */
    public void setFetchAll(Boolean fetchAll) {
        this.fetchAll = fetchAll;
    }

    /**
     * @param pageNo
     * @return this object
     */
    public QueryLoadBalanceRequest withPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
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
     * @param pageSize
     * @return this object
     */
    public QueryLoadBalanceRequest withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
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
