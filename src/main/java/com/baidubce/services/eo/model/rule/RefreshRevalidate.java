package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The refresh revalidate (ignore client refresh) configuration.
 */
public class RefreshRevalidate extends JsonObject {
    /**
     * true means enabled, false means disabled.
     */
    private Boolean enabled;

    /**
     * @param enabled
     * @return this object
     */
    public RefreshRevalidate withEnabled(Boolean enabled) {
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
}
