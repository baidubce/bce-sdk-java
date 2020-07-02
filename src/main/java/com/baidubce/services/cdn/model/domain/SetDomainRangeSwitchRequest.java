package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * create by changxing01 on 19/8/27
 */
public class SetDomainRangeSwitchRequest extends CdnRequest {
    private String domain;
    private boolean rangeSwitch;

    /**
     * @return rangeSwitch
     */
    public boolean isRangeSwitch() {
        return rangeSwitch;
    }

    /**
     * @param rangeSwitch Whether Range back to source
     */
    public void setRangeSwitch(boolean rangeSwitch) {
        this.rangeSwitch = rangeSwitch;
    }

    /**
     * @param rangeSwitch Whether Range back to source
     * @return this object
     */
    public SetDomainRangeSwitchRequest withRangeSwitch(boolean rangeSwitch) {
        this.rangeSwitch = rangeSwitch;
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
    public SetDomainRangeSwitchRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

}
