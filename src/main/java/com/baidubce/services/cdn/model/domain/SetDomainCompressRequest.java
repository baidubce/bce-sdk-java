package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/28
 */
public class SetDomainCompressRequest extends CdnRequest {
    private String domain;
    private Compress compress;

    /**
     * @return compress
     */
    public Compress getCompress() {
        return compress;
    }

    /**
     * @param compress Detailed configuration of page compression
     */
    public void setCompress(Compress compress) {
        this.compress = compress;
    }

    /**
     * @param compress Detailed configuration of page compression
     * @return this object
     */
    public SetDomainCompressRequest withCompress(Compress compress) {
        this.compress = compress;
        return this;
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
    public SetDomainCompressRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
