package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by changxing01 on 19/8/27
 */
public class GetDomainClientIpResponse extends CdnResponse {
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
}
