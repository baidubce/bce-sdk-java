package com.baidubce.services.cdn.model.dsa;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/28
 */
public class SetDomainDsaRequest extends CdnRequest {
    private String domain;
    private DSA dsa;

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainDsaRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * @return dsa
     */
    public DSA getDsa() {
        return dsa;
    }

    /**
     * @param dsa Configuration structure
     */
    public void setDsa(DSA dsa) {
        this.dsa = dsa;
    }

    /**
     * @param dsa Configuration structure
     * @return this object
     */
    public SetDomainDsaRequest withDsa(DSA dsa) {
        this.dsa = dsa;
        return this;
    }
}
