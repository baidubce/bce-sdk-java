package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The webSocket configuration.
 */
public class WebSocket extends JsonObject {
    /**
     * true means enabled, false means disabled.
     */
    private Boolean enabled;

    /**
     * The max connection timeout in seconds, range 1-300. Required when enabled, absent when disabled.
     */
    private Integer timeout;

    /**
     * @param enabled
     * @return this object
     */
    public WebSocket withEnabled(Boolean enabled) {
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
     * @param timeout
     * @return this object
     */
    public WebSocket withTimeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * @return timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * @param timeout
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
