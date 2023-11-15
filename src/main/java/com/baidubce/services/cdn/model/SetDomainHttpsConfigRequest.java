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

import com.baidubce.services.cdn.model.domain.HttpsConfig;

public class SetDomainHttpsConfigRequest extends CdnRequest {
    private String domain;
    private HttpsConfig https;
    
    public String getDomain() {
        return domain;
    }
    
    public void setDomain(String domain) {
        this.domain = domain;
    }
    
    public HttpsConfig getHttps() {
        return https;
    }
    
    public void setHttps(HttpsConfig https) {
        this.https = https;
    }
    
    public SetDomainHttpsConfigRequest withDomain(String domain) {
        setDomain(domain);
        return this;
    }
    
    public SetDomainHttpsConfigRequest withHttps(HttpsConfig https) {
        setHttps(https);
        return this;
    }
}
