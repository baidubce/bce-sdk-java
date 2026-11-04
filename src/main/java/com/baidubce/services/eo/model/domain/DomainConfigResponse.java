package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoResponse;

/**
 * The response of querying an accelerate domain configuration.
 */
public class DomainConfigResponse extends EoResponse {
    /**
     * The accelerate domain.
     */
    private String domain;

    /**
     * The detailed configuration.
     */
    private DomainConfig config;

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
