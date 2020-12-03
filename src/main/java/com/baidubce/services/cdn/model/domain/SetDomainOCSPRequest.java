package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * Online Certificate Status Protocol
 */
public class SetDomainOCSPRequest extends CdnRequest {

    private String domain;
    private boolean OCSP;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isOCSP() {
        return OCSP;
    }

    public void setOCSP(boolean OCSP) {
        this.OCSP = OCSP;
    }

    /**
     * @param OCSP
     * @return this object
     */
    public SetDomainOCSPRequest withOCSP(boolean OCSP) {
        setOCSP(OCSP);
        return this;
    }


    /**
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainOCSPRequest withDomain(String domain) {
        setDomain(domain);
        return this;
    }

}
