package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/27
 */
public class SetDomainMobileAccessRequest extends CdnRequest {
    private String domain;
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

    /**
     * @param mobileAccess Mobile access control
     * @return this object
     */
    public SetDomainMobileAccessRequest withMobileAccess(MobileAccess mobileAccess) {
        this.mobileAccess = mobileAccess;
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
    public SetDomainMobileAccessRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

}
