package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/27
 */
public class ErrorPage extends JsonObject {
    private int code;
    private String url;
    private int redirectCode;

    public ErrorPage() {
    }

    public ErrorPage(int code, String url) {
        this.code = code;
        this.url = url;
    }

    public ErrorPage(int code, String url, int redirectCode) {
        this.code = code;
        this.url = url;
        this.redirectCode = redirectCode;
    }

    /**
     * @return code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code status code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url redirect path
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return redirectCode
     */
    public int getRedirectCode() {
        return redirectCode;
    }

    /**
     * @param redirectCode redirect status code
     */
    public void setRedirectCode(int redirectCode) {
        this.redirectCode = redirectCode;
    }
}
