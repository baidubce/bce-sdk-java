package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by changxing01 on 19/8/27
 */
public class GetDomainAccessLimitResponse extends CdnResponse {
    private AccessLimit accessLimit;

    /**
     * @return accessLimit
     */
    public AccessLimit getAccessLimit() {
        return accessLimit;
    }

    /**
     * @param accessLimit IP access frequency limit control information
     */
    public void setAccessLimit(AccessLimit accessLimit) {
        this.accessLimit = accessLimit;
    }
}
