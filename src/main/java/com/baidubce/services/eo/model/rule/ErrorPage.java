package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The custom error page configuration.
 */
public class ErrorPage extends JsonObject {
    /**
     * The specific error status code.
     */
    private Integer code;

    /**
     * The redirect target url when the given code error occurs.
     */
    private String url;

    /**
     * @param code
     * @return this object
     */
    public ErrorPage withCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @param url
     * @return this object
     */
    public ErrorPage withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
