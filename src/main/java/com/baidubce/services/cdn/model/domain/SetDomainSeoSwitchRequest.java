package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/28
 */
public class SetDomainSeoSwitchRequest extends CdnRequest {
    private String domain;
    private SeoSwitch seoSwitch;

    /**
     * @return seoSwitch
     */
    public SeoSwitch getSeoSwitch() {
        return seoSwitch;
    }

    /**
     * @param seoSwitch Set seo switch properties
     */
    public void setSeoSwitch(SeoSwitch seoSwitch) {
        this.seoSwitch = seoSwitch;
    }

    /**
     * @param seoSwitch Set seo switch properties
     * @return this object
     */
    public SetDomainSeoSwitchRequest withSeoSwitch(SeoSwitch seoSwitch) {
        this.seoSwitch = seoSwitch;
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
    public SetDomainSeoSwitchRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
