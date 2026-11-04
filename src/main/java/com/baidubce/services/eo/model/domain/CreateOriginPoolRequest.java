package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for creating an origin pool of a site.
 *
 */
public class CreateOriginPoolRequest extends EoRequest {
    /**
     * The site name.
     */
    private String site;

    /**
     * The origin pool configuration.
     */
    private OriginPool originPool;

    /**
     * @param site
     * @return this object
     */
    public CreateOriginPoolRequest withSite(String site) {
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
     * @param originPool
     * @return this object
     */
    public CreateOriginPoolRequest withOriginPool(OriginPool originPool) {
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
