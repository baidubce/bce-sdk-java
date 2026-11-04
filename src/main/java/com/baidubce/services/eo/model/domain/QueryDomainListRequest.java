package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for querying the accelerate domain list with simple configuration.
 */
public class QueryDomainListRequest extends EoRequest {
    /**
     * The site id.
     */
    private Integer siteId;

    /**
     * The keyword for fuzzy matching domain names.
     */
    private String keyword;

    /**
     * The domain status filter. Case-insensitive.
     */
    private String status;

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
    public QueryDomainListRequest withSiteId(Integer siteId) {
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
     * @param keyword
     * @return this object
     */
    public QueryDomainListRequest withKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    /**
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @param status
     * @return this object
     */
    public QueryDomainListRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param pageNo
     * @return this object
     */
    public QueryDomainListRequest withPageNo(Integer pageNo) {
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
    public QueryDomainListRequest withPageSize(Integer pageSize) {
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
