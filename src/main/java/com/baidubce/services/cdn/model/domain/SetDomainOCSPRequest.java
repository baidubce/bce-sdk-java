package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * Online Certificate Status Protocol
 */
public class SetDomainOCSPRequest extends CdnRequest {

    private String domain;
    private boolean ocsp;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isOcsp() {
        return ocsp;
    }

    public void setOcsp(boolean ocsp) {
        this.ocsp = ocsp;
    }

    /**
     * @param ocsp
     * @return this object
     */
    public SetDomainOCSPRequest withOcsp(boolean ocsp) {
        setOcsp(ocsp);
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
