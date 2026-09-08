package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The browser cache TTL configuration of a site.
 */
public class ClientCacheTtl extends JsonObject {
    /**
     * The cache mode. Valid values are "follow" (follow the origin), "no_cache" and "custom".
     */
    private String mode;

    /**
     * The cache time in seconds. Only effective when mode is "custom".
     */
    private Integer ttl;

    /**
     * @param mode
     * @return this object
     */
    public ClientCacheTtl withMode(String mode) {
        this.mode = mode;
        return this;
    }

    /**
     * @return mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @param ttl
     * @return this object
     */
    public ClientCacheTtl withTtl(Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * @return ttl
     */
    public Integer getTtl() {
        return ttl;
    }

    /**
     * @param ttl
     */
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
