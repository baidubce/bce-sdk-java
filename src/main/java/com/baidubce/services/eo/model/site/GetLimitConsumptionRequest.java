package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for querying consumption limit rules of a site.
 *
 * <p>Corresponds to {@code POST /v2/geo/limit-consumption?action=query}.</p>
 */
public class GetLimitConsumptionRequest extends EoRequest {
    /**
     * The site name.
     */
    private String site;

    /**
     * The target object filter. Only an accelerating domain is supported as a filter.
     */
    private String object;

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
    public GetLimitConsumptionRequest withSite(String site) {
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
     * @param object
     * @return this object
     */
    public GetLimitConsumptionRequest withObject(String object) {
        this.object = object;
        return this;
    }

    /**
     * @return object
     */
    public String getObject() {
        return object;
    }

    /**
     * @param object
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * @param pageNo
     * @return this object
     */
    public GetLimitConsumptionRequest withPageNo(Integer pageNo) {
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
    public GetLimitConsumptionRequest withPageSize(Integer pageSize) {
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
