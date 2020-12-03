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

import java.util.ArrayList;
import java.util.List;

/**
 * update by changxing01 on 19/8/28
 */
public class HttpsConfig extends JsonObject {
    private boolean enabled;
    private String certId;
    private boolean httpRedirect;
    private boolean httpOrigin;
    private int httpRedirectCode;
    private boolean httpsRedirect;
    private int httpsRedirectCode;
    private boolean http2Enabled;
    private String sslVersion;
    private List<String> sslProtocols;

    /**
     * @param protocol
     * @return
     */
    public HttpsConfig addSslProtocols(String protocol) {
        if (sslProtocols == null) {
            sslProtocols = new ArrayList<String>();
        }
        sslProtocols.add(protocol);
        return this;
    }

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
     * @param httpRedirect http redirect code
     * @return this object
     */
    public HttpsConfig withHttpRedirect(boolean httpRedirect) {
        this.httpRedirect = httpRedirect;
        return this;
    }

    /**
     * @param httpOrigin HTTP protocol source
     * @return this object
     */
    public HttpsConfig withHttpOrigint(boolean httpOrigin) {
        this.httpOrigin = httpOrigin;
        return this;
    }

    /**
     * @param sslVersion TLS version
     * @return this object
     */
    public HttpsConfig withSslVersion(String sslVersion) {
        this.sslVersion = sslVersion;
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

    public boolean isHttpRedirect() {
        return httpRedirect;
    }

    public void setHttpRedirect(boolean httpRedirect) {
        this.httpRedirect = httpRedirect;
    }

    public boolean isHttpOrigin() {
        return httpOrigin;
    }

    public void setHttpOrigin(boolean httpOrigin) {
        this.httpOrigin = httpOrigin;
    }

    public String getSslVersion() {
        return sslVersion;
    }

    public void setSslVersion(String sslVersion) {
        this.sslVersion = sslVersion;
    }

    public int getHttpRedirectCode() {
        return httpRedirectCode;
    }

    public void setHttpRedirectCode(int httpRedirectCode) {
        this.httpRedirectCode = httpRedirectCode;
    }

    public boolean isHttpsRedirect() {
        return httpsRedirect;
    }

    public void setHttpsRedirect(boolean httpsRedirect) {
        this.httpsRedirect = httpsRedirect;
    }

    public int getHttpsRedirectCode() {
        return httpsRedirectCode;
    }

    public void setHttpsRedirectCode(int httpsRedirectCode) {
        this.httpsRedirectCode = httpsRedirectCode;
    }

    public boolean isHttp2Enabled() {
        return http2Enabled;
    }

    public void setHttp2Enabled(boolean http2Enabled) {
        this.http2Enabled = http2Enabled;
    }

    public List<String> getSslProtocols() {
        return sslProtocols;
    }

    public void setSslProtocols(List<String> sslProtocols) {
        this.sslProtocols = sslProtocols;
    }
}
