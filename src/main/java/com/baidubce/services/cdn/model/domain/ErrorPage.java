package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/27
 */
public class ErrorPage extends JsonObject {

    /**
     * 特定的状态码，要求必须为HTTP的标准错误码，且不能是408、444、499等客户端异常/提前断开这类特殊状态码
     * 必选
     */
    private Integer code;

    /**
     * 重定向目标地址 ，当出现code错误码是，重定向到这个用户自定义的url
     * 必选
     */
    private String url;

    /**
     * 重定向状态码，当出现code错误码时，重定向的类型。支持301和302，默认302
     * 可选
     */
    private Integer redirectCode;

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

    public ErrorPage withCode(Integer code) {
        this.code = code;
        return this;
    }

    public ErrorPage withUrl(String url) {
        this.url = url;
        return this;
    }

    public ErrorPage withRedirectCode(Integer redirectCode) {
        this.redirectCode = redirectCode;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRedirectCode() {
        return redirectCode;
    }

    public void setRedirectCode(Integer redirectCode) {
        this.redirectCode = redirectCode;
    }
}
