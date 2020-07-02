package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class CompressResponse extends JsonObject {
    private String allow;
    private String type;

    /**
     * @return allow
     */
    public String getAllow() {
        return allow;
    }

    /**
     * @param allow Whether to enable page compression
     */
    public void setAllow(String allow) {
        this.allow = allow;
    }

    /**
     * @param allow Whether to enable page compression
     * @return this object
     */
    public CompressResponse withAllow(String allow) {
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
    public CompressResponse withType(String type) {
        this.type = type;
        return this;
    }
}
