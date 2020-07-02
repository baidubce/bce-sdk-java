package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;
import com.baidubce.services.cdn.model.RefererACL;

/**
 * create by changxing01 on 19/9/3
 */
public class GetDomainRefererACLResponse extends CdnResponse {
    private RefererACL refererACL;

    /**
     * @return refererACL
     */
    public RefererACL getRefererACL() {
        return refererACL;
    }

    /**
     * @param refererACL referer config rules
     */
    public void setRefererACL(RefererACL refererACL) {
        this.refererACL = refererACL;
    }
}
