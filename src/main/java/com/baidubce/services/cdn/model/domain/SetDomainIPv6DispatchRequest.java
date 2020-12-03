package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * IPv6
 */
public class SetDomainIPv6DispatchRequest extends CdnRequest {

    public Enable getIPv6Dispatch() {
        return IPv6Dispatch;
    }

    public void setIPv6Dispatch(Enable IPv6Dispatch) {
        this.IPv6Dispatch = IPv6Dispatch;
    }

    private Enable IPv6Dispatch;

    public SetDomainIPv6DispatchRequest withIPv6Dispatch(Enable enable) {
        setIPv6Dispatch(enable);
        System.out.println(this.toString());
        return this;
    }

}
