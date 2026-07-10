package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The real ip configuration.
 */
public class RealIp extends JsonObject {
    /**
     * true means enabled, false means disabled.
     */
    private Boolean enabled;

    /**
     * The header name to carry the client ip. Valid values are "True-Client-Ip", "X-Real-IP".
     * Required when enabled, absent when disabled.
     */
    private String name;

    /**
     * @param enabled
     * @return this object
     */
    public RealIp withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @param name
     * @return this object
     */
    public RealIp withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
