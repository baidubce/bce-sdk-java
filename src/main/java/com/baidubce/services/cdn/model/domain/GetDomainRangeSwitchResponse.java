package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * create by changxing01 on 19/8/27
 */
public class GetDomainRangeSwitchResponse extends CdnResponse {
    private String rangeSwitch;

    /**
     * @return rangeSwitch
     */
    public String getRangeSwitch() {
        return rangeSwitch;
    }

    /**
     * @param rangeSwitch Whether Range back to source
     */
    public void setRangeSwitch(String rangeSwitch) {
        this.rangeSwitch = rangeSwitch;
    }
}
