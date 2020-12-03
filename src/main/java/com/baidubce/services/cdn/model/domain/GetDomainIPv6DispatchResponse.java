package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by hansongda on 20/12/01
 */
public class GetDomainIPv6DispatchResponse extends CdnResponse {
    private Enable IPv6Dispatch;

    public Enable getIPv6Dispatch() {
        return IPv6Dispatch;
    }

    public void setIPv6Dispatch(Enable IPv6Dispatch) {
        this.IPv6Dispatch = IPv6Dispatch;
    }
}
