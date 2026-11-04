package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for deleting a consumption limit rule of a site or domain.
 *
 * <p>Corresponds to {@code POST /v2/geo/limit-consumption?action=delete}.</p>
 */
public class DeleteLimitConsumptionRequest extends EoRequest {
    /**
     * The site name.
     */
    private String site;

    /**
     * The target object. "site" means the site itself, otherwise it is a domain under the site.
     */
    private String object;

    /**
     * @param site
     * @return this object
     */
    public DeleteLimitConsumptionRequest withSite(String site) {
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
    public DeleteLimitConsumptionRequest withObject(String object) {
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
}
