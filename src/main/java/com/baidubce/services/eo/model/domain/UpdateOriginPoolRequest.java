package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for updating an origin pool of a site.
 *
 */
public class UpdateOriginPoolRequest extends EoRequest {
    /**
     * The site name.
     */
    private String site;

    /**
     * The origin pool id.
     */
    private String originPoolId;

    /**
     * The origin pool configuration.
     */
    private OriginPool originPool;

    /**
     * @param site
     * @return this object
     */
    public UpdateOriginPoolRequest withSite(String site) {
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
    public UpdateOriginPoolRequest withOriginPoolId(String originPoolId) {
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
     * @param originPool
     * @return this object
     */
    public UpdateOriginPoolRequest withOriginPool(OriginPool originPool) {
        this.originPool = originPool;
        return this;
    }

    /**
     * @return originPool
     */
    public OriginPool getOriginPool() {
        return originPool;
    }

    /**
     * @param originPool
     */
    public void setOriginPool(OriginPool originPool) {
        this.originPool = originPool;
    }
}
