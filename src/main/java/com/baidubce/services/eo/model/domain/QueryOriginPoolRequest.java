package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for querying origin pools of a site.
 *
 */
public class QueryOriginPoolRequest extends EoRequest {
    /**
     * The site name.
     */
    private String site;

    /**
     * The origin pool name filter.
     */
    private String name;

    /**
     * The origin pool id filter.
     */
    private String originPoolId;

    /**
     * The page number, defaults to 1.
     */
    private Integer pageNo;

    /**
     * The page size, defaults to 10.
     */
    private Integer pageSize;

    /**
     * @param site
     * @return this object
     */
    public QueryOriginPoolRequest withSite(String site) {
        this.site = site;
        return this;
    }

    /**
     * @return site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @param name
     * @return this object
     */
    public QueryOriginPoolRequest withName(String name) {
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
     * @param originPoolId
     * @return this object
     */
    public QueryOriginPoolRequest withOriginPoolId(String originPoolId) {
        this.originPoolId = originPoolId;
        return this;
    }

    /**
     * @return originPoolId
     */
    public String getOriginPoolId() {
        return originPoolId;
    }

    /**
     * @param originPoolId
     */
    public void setOriginPoolId(String originPoolId) {
        this.originPoolId = originPoolId;
    }

    /**
     * @param pageNo
     * @return this object
     */
    public QueryOriginPoolRequest withPageNo(Integer pageNo) {
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
    public QueryOriginPoolRequest withPageSize(Integer pageSize) {
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
