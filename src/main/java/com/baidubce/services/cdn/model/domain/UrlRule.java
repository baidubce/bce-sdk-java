package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

public class UrlRule extends JsonObject {

    /**
     * 字符串长度小于等于128。
     * 不含http(s)://头和域名。可以匹配参数，比如只改写带有特定参数的URI。支持正则以及捕获，比如(/[^?]+)\?c=1
     * 必选
     */
    private String action;

    /**
     * 字符串长度小于等于128。
     * 不含http(s)://头和域名。最终生成的URI必须以/开头。支持捕获，比如${1}/test。
     * 目标URI里面如果没有?，则会带上原始参数。如果有?，则会用?后面的参数替换原始参数。
     * 比如访问URI为/a?c=1
     * • 待重写URI为/a，目标URI为/b，改写后URI为/b?c=1
     * • 待重写URI为/a，目标URI为/b?，改写后URI为/b
     * • 待重写URI为/a，目标URI为/b?d=1，改写后URI为/b?d=1
     * 必选
     */
    private String src;

    /**
     * 合法值为redirect或者rewrite。
     * redirect：若请求的URI匹配了当前规则，该请求将被302重定向跳转到目标URI。
     * break：若请求的URI匹配了当前规则，执行完当前规则后，将不再匹配剩余规则。
     * 必选
     */
    private String dst;

    public UrlRule() {
    }

    public UrlRule withAction(String action) {
        this.action = action;
        return this;
    }

    public UrlRule withSrc(String src) {
        this.src = src;
        return this;
    }

    public UrlRule withDst(String dst) {
        this.dst = dst;
        return this;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
