package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class SetDomainOriginProtocolRequest extends AbstractBceRequest {

    private String domain;
    /**
     * 必选 值为"https", "http" 或者 "*"，分别代表https回源，http回源，以及协议跟随回源。设置https需要先开启https加速
     */
    private OriginProtocol originProtocol;

    public SetDomainOriginProtocolRequest() {
    }

    public SetDomainOriginProtocolRequest(String domain, OriginProtocol originProtocol) {
        this.domain = domain;
        this.originProtocol = originProtocol;
    }

    public SetDomainOriginProtocolRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public SetDomainOriginProtocolRequest withOriginProtocol(OriginProtocol originProtocol) {
        this.originProtocol = originProtocol;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public OriginProtocol getOriginProtocol() {
        return originProtocol;
    }

    public void setOriginProtocol(OriginProtocol originProtocol) {
        this.originProtocol = originProtocol;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
