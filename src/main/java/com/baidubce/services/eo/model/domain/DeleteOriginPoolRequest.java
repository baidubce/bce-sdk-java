package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for deleting an origin pool of a site.
 *
 */
public class DeleteOriginPoolRequest extends EoRequest {
    /**
     * The site name.
     */
    private String site;

    /**
     * The origin pool id.
     */
    private String originPoolId;

    /**
     * @param site
     * @return this object
     */
    public DeleteOriginPoolRequest withSite(String site) {
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
     * @param originPoolId
     * @return this object
     */
    public DeleteOriginPoolRequest withOriginPoolId(String originPoolId) {
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
}
