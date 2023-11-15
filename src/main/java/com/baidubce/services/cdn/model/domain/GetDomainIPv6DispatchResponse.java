package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainIPv6DispatchResponse extends CdnResponse {
    private Enable ipv6Dispatch;

    public GetDomainIPv6DispatchResponse() {
    }

    public Enable getIpv6Dispatch() {
        return ipv6Dispatch;
    }

    public void setIpv6Dispatch(Enable ipv6Dispatch) {
        this.ipv6Dispatch = ipv6Dispatch;
    }
}
