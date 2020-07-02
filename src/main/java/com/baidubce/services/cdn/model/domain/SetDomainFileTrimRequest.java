package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/28
 */
public class SetDomainFileTrimRequest extends CdnRequest {
    private String domain;
    private boolean fileTrim;

    /**
     * @return fileTrim
     */
    public boolean isFileTrim() {
        return fileTrim;
    }

    /**
     * @param fileTrim Whether to enable page optimization
     * @return this object
     */
    public SetDomainFileTrimRequest withFileTrim(boolean fileTrim) {
        this.fileTrim = fileTrim;
        return this;
    }

    /**
     * @param fileTrim Whether to enable page optimization
     */
    public void setFileTrim(boolean fileTrim) {
        this.fileTrim = fileTrim;
    }

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
    public SetDomainFileTrimRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
