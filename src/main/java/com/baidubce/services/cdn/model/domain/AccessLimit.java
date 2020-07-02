package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/27
 */
public class AccessLimit extends JsonObject {
    private boolean enabled;
    private int limit;

    /**
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled Whether to turn on IP single node access limit
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return enabled
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit Maximum number of requests per IP node in 1 second
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @param enabled Whether to turn on IP single node access limit
     * @return this object
     */
    public AccessLimit withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * @param limit Maximum number of requests per IP node in 1 second
     * @return this object
     */
    public AccessLimit withLimit(int limit) {
        this.limit = limit;
        return this;
    }
}
