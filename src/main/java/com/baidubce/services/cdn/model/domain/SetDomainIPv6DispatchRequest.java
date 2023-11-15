package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * IPv6
 */
public class SetDomainIPv6DispatchRequest extends CdnRequest {
    private String domain;
    private Enable ipv6Dispatch;

    public Enable getIpv6Dispatch() {
        return ipv6Dispatch;
    }

    public void setIpv6Dispatch(Enable ipv6Dispatch) {
        this.ipv6Dispatch = ipv6Dispatch;
    }

    public SetDomainIPv6DispatchRequest withIPv6Dispatch(Enable enable) {
        setIpv6Dispatch(enable);
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
    public SetDomainIPv6DispatchRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

}
