package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for deleting a load balance of a site.
 */
public class DeleteLoadBalanceRequest extends EoRequest {
    /**
     * The site id that the load balance belongs to.
     */
    private Integer siteId;

    /**
     * The load balance id to delete. It is a path parameter, not serialized into the body.
     */
    @JsonIgnore
    private String id;

    /**
     * @param siteId
     * @return this object
     */
    public DeleteLoadBalanceRequest withSiteId(Integer siteId) {
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
     * @param id
     * @return this object
     */
    public DeleteLoadBalanceRequest withId(String id) {
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
}
