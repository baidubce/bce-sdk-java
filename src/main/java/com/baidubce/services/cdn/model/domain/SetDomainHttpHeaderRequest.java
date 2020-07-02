package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class SetDomainHttpHeaderRequest extends CdnRequest {
    private String domain;
    private List<HttpHeader> httpHeader;

    /**
     * @return httpHeader
     */
    public List<HttpHeader> getHttpHeader() {
        return httpHeader;
    }

    /**
     * @param httpHeader Set back source or respond to Header
     */
    public void setHttpHeader(List<HttpHeader> httpHeader) {
        this.httpHeader = httpHeader;
    }

    /**
     * @param httpHeader Set back source or respond to Header
     * @return this object
     */
    public SetDomainHttpHeaderRequest withHttpHeader(List<HttpHeader> httpHeader) {
        this.httpHeader = httpHeader;
        return this;
    }

    /**
     * @param header Set back source or respond to Header
     * @return this object
     */
    public SetDomainHttpHeaderRequest addHttpHeader(HttpHeader header) {
        if (this.httpHeader == null) {
            this.httpHeader = new ArrayList<HttpHeader>();
        }
        this.httpHeader.add(header);
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
    public SetDomainHttpHeaderRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
