package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

import java.util.List;

/**
 * The request for updating an accelerate domain configuration.
 */
public class UpdateDomainConfigRequest extends EoRequest {
    /**
     * The site that the domain belongs to.
     */
    private String site;

    /**
     * The accelerate domain. Has a higher priority than domains.
     */
    private String domain;

    /**
     * The domains to configure in batch.
     */
    private List<String> domains;

    /**
     * The configuration to update.
     */
    private DomainConfig config;

    /**
     * @param site
     * @return this object
     */
    public UpdateDomainConfigRequest withSite(String site) {
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
     * @param domain
     * @return this object
     */
    public UpdateDomainConfigRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domains
     * @return this object
     */
    public UpdateDomainConfigRequest withDomains(List<String> domains) {
        this.domains = domains;
        return this;
    }

    /**
     * @return domains
     */
    public List<String> getDomains() {
        return domains;
    }

    /**
     * @param domains
     */
    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    /**
     * @param config
     * @return this object
     */
    public UpdateDomainConfigRequest withConfig(DomainConfig config) {
        this.config = config;
        return this;
    }

    /**
     * @return config
     */
    public DomainConfig getConfig() {
        return config;
    }

    /**
     * @param config
     */
    public void setConfig(DomainConfig config) {
        this.config = config;
    }
}
