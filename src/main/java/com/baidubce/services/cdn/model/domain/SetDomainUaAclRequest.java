package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class SetDomainUaAclRequest extends AbstractBceRequest {

    private String domain;
    private UaAcl uaAcl;

    public SetDomainUaAclRequest() {
    }

    public SetDomainUaAclRequest(String domain, UaAcl uaAcl) {
        this.domain = domain;
        this.uaAcl = uaAcl;
    }

    public SetDomainUaAclRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public SetDomainUaAclRequest withUaAcl(UaAcl uaAcl) {
        this.uaAcl = uaAcl;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public UaAcl getUaAcl() {
        return uaAcl;
    }

    public void setUaAcl(UaAcl uaAcl) {
        this.uaAcl = uaAcl;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
