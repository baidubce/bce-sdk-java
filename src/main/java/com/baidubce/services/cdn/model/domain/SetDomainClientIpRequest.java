package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/27
 */
public class SetDomainClientIpRequest extends CdnRequest {
    private String domain;
    private ClientIp clientIp;

    /**
     * @return clientIp
     */
    public ClientIp getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp Turn it ON or OFF and the IP type
     */
    public void setClientIp(ClientIp clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @param clientIp Turn it ON or OFF and the IP type
     * @return this object
     */
    public SetDomainClientIpRequest withClientIp(ClientIp clientIp) {
        this.clientIp = clientIp;
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
    public SetDomainClientIpRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
