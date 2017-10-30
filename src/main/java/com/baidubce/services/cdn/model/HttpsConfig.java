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

package com.baidubce.services.cdn.model;

public class HttpsConfig extends JsonObject {
    private boolean enabled;
    private String certId;
    private boolean httpRedirect;
    private boolean httpOrigin;
    private String sllVersion;
    
    public HttpsConfig withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
    
    public HttpsConfig withCertId(String certId) {
        this.certId = certId;
        return this;
    }
    
    public HttpsConfig withHttpRedirect(boolean httpRedirect) {
        this.httpRedirect = httpRedirect;
        return this;
    }
    
    public HttpsConfig withHttpOrigint(boolean httpOrigin) {
        this.httpOrigin = httpOrigin;
        return this;
    }
    
    public HttpsConfig withSllVersion(String sllVersion) {
        this.sllVersion = sllVersion;
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
    
    public String getSllVersion() {
        return sllVersion;
    }
    
    public void setSllVersion(String sllVersion) {
        this.sllVersion = sllVersion;
    }
}
