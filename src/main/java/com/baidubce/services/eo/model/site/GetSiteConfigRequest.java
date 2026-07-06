package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for querying configuration of a site.
 */
public class GetSiteConfigRequest extends EoRequest {
    /**
     * The site to be queried.
     */
    private String site;

    /**
     * @param site
     * @return this object
     */
    public GetSiteConfigRequest withSite(String site) {
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
}
