package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainSeoSwitchResponse extends CdnResponse {
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

}
