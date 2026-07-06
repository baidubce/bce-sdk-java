package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The HSTS (HTTP Strict Transport Security) configuration of a site.
 *
 */
public class Hsts extends JsonObject {
    /**
     * The saved time in days. Valid values are 0 ~ 730 or -1. -1 means this config item is disabled.
     */
    private Integer maxAge;

    /**
     * Whether to include subdomains.
     */
    private Boolean includeSubDomains;

    /**
     * Whether to support preload.
     */
    private Boolean preload;

    /**
     * @param maxAge
     * @return this object
     */
    public Hsts withMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    /**
     * @return maxAge
     */
    public Integer getMaxAge() {
        return maxAge;
    }

    /**
     * @param maxAge
     */
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * @param includeSubDomains
     * @return this object
     */
    public Hsts withIncludeSubDomains(Boolean includeSubDomains) {
        this.includeSubDomains = includeSubDomains;
        return this;
    }

    /**
     * @return includeSubDomains
     */
    public Boolean getIncludeSubDomains() {
        return includeSubDomains;
    }

    /**
     * @param includeSubDomains
     */
    public void setIncludeSubDomains(Boolean includeSubDomains) {
        this.includeSubDomains = includeSubDomains;
    }

    /**
     * @param preload
     * @return this object
     */
    public Hsts withPreload(Boolean preload) {
        this.preload = preload;
        return this;
    }

    /**
     * @return preload
     */
    public Boolean getPreload() {
        return preload;
    }

    /**
     * @param preload
     */
    public void setPreload(Boolean preload) {
        this.preload = preload;
    }
}
