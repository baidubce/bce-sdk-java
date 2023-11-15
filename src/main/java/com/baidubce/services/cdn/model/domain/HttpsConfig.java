/*
 * Copyright 2016 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.List;

/**
 * update by changxing01 on 19/8/28
 */
public class HttpsConfig {

    /**
     * 开启HTTPS加速(服务端HTTPS认证)，默认为false，当enabled=false，以下几列字段设置无效（当certId=""时certId字段是有效的）
     * 必选
     */
    private boolean enabled;

    /**
     * 当enabled=true时此项为必选，为SSL证书服务返回的证书ID，
     * 当enablae为false且certId为""时解绑domain当前绑定的certId，否则当enabled=False时此项无效
     * 可选
     */
    private String certId;

    /**
     * 为true时将HTTP请求重定向到HTTPS（重定向状态码为httpRedirectCode所配置），
     * 默认为false，当enabled=false此项无效，不可与httpsRedirect同时为true
     * 可选
     */
    private Boolean httpRedirect;

    /**
     * 重定向状态码，可选值301/302，默认302，当enabled=false此项无效，httpRedirect=false此项无效
     * 可选
     */
    private Integer httpRedirectCode;

    /**
     * 为true时将HTTPS请求重定向到HTTP重定向状态码为httpsRedirectCode所配置），
     * 默认为false，当enabled=false此项无效，不可与httpRedirect同时为true
     * 可选
     */
    private Boolean httpsRedirect;

    /**
     * 重定向状态码，可选值301/302，默认302，当enabled=false此项无效，httpsRedirect=false此项无效
     * 可选
     */
    private Integer httpsRedirectCode;

    /**
     * 开启HTTP2特性，默认true，当enabled=false此项无效
     * 可选
     */
    private Boolean http2Enabled;

    /**
     * 为true时开启HTTPS双向认证。只有开启了服务端HTTPS认证时可以开启该配置，默认为false
     * 可选
     */
    private Boolean verifyClient;

    /**
     * 设置访问TLS版本，默认为支持从TLSv1.0到TLSv1.3的版本，
     * 也可以主动设置为以下四个中的一个或多个，"TLSv1.0","TLSv1.1","TLSv1.2","TLSv1.3"。
     * 该参数不能为空list。当enabled=false时此项无效。此项一般取默认值，无需设置
     *
     * 可选
     */
    private List<String> sslProtocols;

    /**
     * 跳转时排除包含该字段ua值的请求
     * 批量上传时使用
     *
     * 可选
     */
    private String disableHttpsSpider;

    /**
     * @param httpRedirectCode http redirect code
     * @return this object
     */
    public HttpsConfig withHttpRedirectCode(int httpRedirectCode) {
        this.httpRedirectCode = httpRedirectCode;
        return this;
    }

    /**
     * @param httpsRedirect https redirect
     * @return this object
     */
    public HttpsConfig withHttpsRedirect(boolean httpsRedirect) {
        this.httpsRedirect = httpsRedirect;
        return this;
    }

    /**
     * @param httpsRedirectCode https redirect code
     * @return this object
     */
    public HttpsConfig withHttpsRedirectCode(int httpsRedirectCode) {
        this.httpsRedirectCode = httpsRedirectCode;
        return this;
    }

    /**
     * @param http2Enabled enable HTTP2 feature
     * @return this object
     */
    public HttpsConfig withHttp2Enabled(boolean http2Enabled) {
        this.http2Enabled = http2Enabled;
        return this;
    }

    /**
     * @param enabled Turn on HTTPS acceleration
     * @return this object
     */
    public HttpsConfig withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * @param certId certificate id
     * @return this object
     */
    public HttpsConfig withCertId(String certId) {
        this.certId = certId;
        return this;
    }

    /**
     * @param disableHttpsSpider 跳转时排除包含该字段ua值的请求
     * @return this object
     */
    public HttpsConfig withDisableHttpsSpider(String disableHttpsSpider) {
        this.disableHttpsSpider = disableHttpsSpider;
        return this;
    }

    /**
     * @param httpRedirect http redirect code
     * @return this object
     */
    public HttpsConfig withHttpRedirect(boolean httpRedirect) {
        this.httpRedirect = httpRedirect;
        return this;
    }

    /**
     * @param verifyClient HTTP protocol source
     * @return this object
     */
    public HttpsConfig withVerifyClient(boolean verifyClient) {
        this.verifyClient = verifyClient;
        return this;
    }

    /**
     * @param sslProtocols TLS version
     * @return this object
     */
    public HttpsConfig withSslProtocols(List<String> sslProtocols) {
        this.sslProtocols = sslProtocols;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public Boolean getHttpRedirect() {
        return httpRedirect;
    }

    public void setHttpRedirect(Boolean httpRedirect) {
        this.httpRedirect = httpRedirect;
    }

    public Integer getHttpRedirectCode() {
        return httpRedirectCode;
    }

    public void setHttpRedirectCode(Integer httpRedirectCode) {
        this.httpRedirectCode = httpRedirectCode;
    }

    public Boolean getHttpsRedirect() {
        return httpsRedirect;
    }

    public void setHttpsRedirect(Boolean httpsRedirect) {
        this.httpsRedirect = httpsRedirect;
    }

    public Integer getHttpsRedirectCode() {
        return httpsRedirectCode;
    }

    public void setHttpsRedirectCode(Integer httpsRedirectCode) {
        this.httpsRedirectCode = httpsRedirectCode;
    }

    public Boolean getHttp2Enabled() {
        return http2Enabled;
    }

    public void setHttp2Enabled(Boolean http2Enabled) {
        this.http2Enabled = http2Enabled;
    }

    public Boolean getVerifyClient() {
        return verifyClient;
    }

    public void setVerifyClient(Boolean verifyClient) {
        this.verifyClient = verifyClient;
    }

    public List<String> getSslProtocols() {
        return sslProtocols;
    }

    public void setSslProtocols(List<String> sslProtocols) {
        this.sslProtocols = sslProtocols;
    }

    public String getDisableHttpsSpider() {
        return disableHttpsSpider;
    }

    public void setDisableHttpsSpider(String disableHttpsSpider) {
        this.disableHttpsSpider = disableHttpsSpider;
    }
}
