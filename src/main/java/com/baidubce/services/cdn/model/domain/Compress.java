package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class Compress extends JsonObject {
    private boolean allow;
    private String type;

    /**
     * @return allow
     */
    public boolean isAllow() {
        return allow;
    }

    /**
     * @param allow Whether to enable page compression
     */
    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    /**
     * @param allow Whether to enable page compression
     * @return this object
     */
    public Compress withAllow(boolean allow) {
        this.allow = allow;
        return this;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type compress type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param type compress type
     * @return this object
     */
    public Compress withType(String type) {
        this.type = type;
        return this;
    }
}
