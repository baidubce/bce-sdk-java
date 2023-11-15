package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * Quick UDP Internet Connection(QUIC)
 */
public class SetDomainQUICRequest extends CdnRequest {
    private String domain;
    private boolean quic;

    public boolean isQuic() {
        return quic;
    }

    public void setQuic(boolean quic) {
        this.quic = quic;
    }

    /**
     * @param quic
     * @return this object
     */
    public SetDomainQUICRequest withQuic(boolean quic) {
        setQuic(quic);
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
    public SetDomainQUICRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
