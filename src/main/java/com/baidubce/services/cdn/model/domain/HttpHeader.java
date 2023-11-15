package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class HttpHeader extends JsonObject {

    /**
     * "origin"表示此header 回源生效，"response"表示给用户响应时生效
     * 必选
     */
    private String type;

    /**
     * header为http头字段，一般为HTTP的标准Header，也可以是用户自定义的；如x-bce-authoriztion
     * 必选
     */
    private String header;

    /**
     * 指定header的值。可支持有限个数的变量，字符$开始的子串一定要有其一符合 ${x} 的模式，
     * 且x必须与以下字符串之一相等：uri、host、scheme和request_uri。
     * 典型非法值：
     *      1、变量不符合限制要求，如"X-REQ-${url}"，我们定义的合法变量里没有url；
     *      2、包含$字符但不符合${x}模式，如："X-REQ-$uri"。
     * 注意：value不支持$纯字符的传递，如果您希望响应客户端一个包含$符号的响应头将不被允许，如"X-$"是非法的。
     * 必选
     */
    private String value;

    /**
     * 表示是删除还是添加，可选remove/add，默认是add
     * 可选
     */
    private String action;

    /**
     * 描述，可选，可以是中文，统一使用Unicode统码；长度不能超过100个字符
     * 可选
     */
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
