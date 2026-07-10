package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The origin timeout configuration.
 */
public class OriginTimeout extends JsonObject {
    /**
     * The HTTP response timeout in seconds, range 5-300.
     */
    private Integer loadTimeout;

    /**
     * The TCP connect timeout in seconds, range 5-30.
     */
    private Integer connectTimeout;

    /**
     * @param loadTimeout
     * @return this object
     */
    public OriginTimeout withLoadTimeout(Integer loadTimeout) {
        this.loadTimeout = loadTimeout;
        return this;
    }

    /**
     * @return loadTimeout
     */
    public Integer getLoadTimeout() {
        return loadTimeout;
    }

    /**
     * @param loadTimeout
     */
    public void setLoadTimeout(Integer loadTimeout) {
        this.loadTimeout = loadTimeout;
    }

    /**
     * @param connectTimeout
     * @return this object
     */
    public OriginTimeout withConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * @return connectTimeout
     */
    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * @param connectTimeout
     */
    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
