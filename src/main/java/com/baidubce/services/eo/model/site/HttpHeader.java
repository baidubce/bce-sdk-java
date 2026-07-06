package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The custom HTTP header rule of a site.
 */
public class HttpHeader extends JsonObject {
    /**
     * "origin" takes effect when back to origin, "response" takes effect when responding to the user.
     */
    private String type;

    /**
     * The HTTP header field name.
     */
    private String header;

    /**
     * The value of the header.
     */
    private String value;

    /**
     * The action.
     */
    private String action;

    /**
     * @param type
     * @return this object
     */
    public HttpHeader withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param header
     * @return this object
     */
    public HttpHeader withHeader(String header) {
        this.header = header;
        return this;
    }

    /**
     * @return header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @param value
     * @return this object
     */
    public HttpHeader withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param action
     * @return this object
     */
    public HttpHeader withAction(String action) {
        this.action = action;
        return this;
    }

    /**
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }
}
