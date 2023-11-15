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

import com.baidubce.services.cdn.model.CdnResponse;
import com.baidubce.services.cdn.model.OriginPeer;

import java.util.List;

/**
 * @author yixing
 * update by changxing01 19/8/28
 *
 */
public class GetDomainConfigResponse extends CdnResponse {
    private String domain;
    private String cname;
    private String status;
    private String createTime;
    private String lastModifyTime;
    private Boolean isBan;
    private String form;
    private List<OriginPeer> origin;
    private String defaultHost;
    private List<CacheTTL> cacheTTL;
    private Integer limitRate;
    private RequestAuth requestAuth;
    private HttpsConfig https;
    private Boolean followProtocol;
    private SeoSwitch seoSwitch;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Boolean getBan() {
        return isBan;
    }

    public void setBan(Boolean ban) {
        isBan = ban;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public List<OriginPeer> getOrigin() {
        return origin;
    }

    public void setOrigin(List<OriginPeer> origin) {
        this.origin = origin;
    }

    public String getDefaultHost() {
        return defaultHost;
    }

    public void setDefaultHost(String defaultHost) {
        this.defaultHost = defaultHost;
    }

    public List<CacheTTL> getCacheTTL() {
        return cacheTTL;
    }

    public void setCacheTTL(List<CacheTTL> cacheTTL) {
        this.cacheTTL = cacheTTL;
    }

    public Integer getLimitRate() {
        return limitRate;
    }

    public void setLimitRate(Integer limitRate) {
        this.limitRate = limitRate;
    }

    public RequestAuth getRequestAuth() {
        return requestAuth;
    }

    public void setRequestAuth(RequestAuth requestAuth) {
        this.requestAuth = requestAuth;
    }

    public HttpsConfig getHttps() {
        return https;
    }

    public void setHttps(HttpsConfig https) {
        this.https = https;
    }

    public Boolean getFollowProtocol() {
        return followProtocol;
    }

    public void setFollowProtocol(Boolean followProtocol) {
        this.followProtocol = followProtocol;
    }

    public SeoSwitch getSeoSwitch() {
        return seoSwitch;
    }

    public void setSeoSwitch(SeoSwitch seoSwitch) {
        this.seoSwitch = seoSwitch;
    }
}
