package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

import java.util.List;

/**
 * The request for a domain operation (start/stop/delete) that selects domains by name.
 */
public class DomainOperationRequest extends EoRequest {
    /**
     * The accelerate domain. Has a higher priority than domains.
     */
    private String domain;

    /**
     * The accelerate domains to operate in batch.
     */
    private List<String> domains;

    /**
     * @param domain
     * @return this object
     */
    public DomainOperationRequest withDomain(String domain) {
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
    public DomainOperationRequest withDomains(List<String> domains) {
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
}
