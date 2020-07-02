package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class HttpHeader extends JsonObject {
    private String type;
    private String header;
    private String value;
    private String action;
    private String describe;

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type Effective type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param type Effective type
     * @return this object
     */
    public HttpHeader withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header Http header field
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @param header Http header field
     * @return this object
     */
    public HttpHeader withHeader(String header) {
        this.header = header;
        return this;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value Header value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param value Header value
     * @return this object
     */
    public HttpHeader withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action remove/add
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @param action remove/add
     * @return this object
     */
    public HttpHeader withAction(String action) {
        this.action = action;
        return this;
    }

    /**
     * @return describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe description
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @param describe description
     * @return this object
     */
    public HttpHeader withDescribe(String describe) {
        this.describe = describe;
        return this;
    }
}
