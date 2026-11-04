package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The ipv6 dispatch configuration of a domain.
 */
public class Ipv6Dispatch extends JsonObject {
    /**
     * The ipv6 switch. "ON" to enable, "OFF" to disable.
     */
    private String enable;

    /**
     * @param enable
     * @return this object
     */
    public Ipv6Dispatch withEnable(String enable) {
        this.enable = enable;
        return this;
    }

    /**
     * @return enable
     */
    public String getEnable() {
        return enable;
    }

    /**
     * @param enable
     */
    public void setEnable(String enable) {
        this.enable = enable;
    }
}
