package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class Compress extends JsonObject {

    /**
     * true表示开启页面压缩，false表示关闭页面压缩
     * 必选
     */
    private boolean allow;

    /**
     * 值为"br"或者"gzip"或者"all"，分别表示支持br，gzip以及br和gzip都支持
     * 必选
     */
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
