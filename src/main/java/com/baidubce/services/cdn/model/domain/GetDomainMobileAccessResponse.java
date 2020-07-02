package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by changxing01 on 19/8/27
 */
public class GetDomainMobileAccessResponse extends CdnResponse {

    private MobileAccess mobileAccess;

    /**
     * @return mobileAccess
     */
    public MobileAccess getMobileAccess() {
        return mobileAccess;
    }

    /**
     * @param mobileAccess Mobile access control
     */
    public void setMobileAccess(MobileAccess mobileAccess) {
        this.mobileAccess = mobileAccess;
    }
}
