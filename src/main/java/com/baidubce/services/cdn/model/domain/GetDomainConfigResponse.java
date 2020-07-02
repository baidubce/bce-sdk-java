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
    private boolean isBan;
    private List<OriginPeer> origin;
    private List<CacheTTL> cacheTTL;
    private Integer limitRate;
    private String form;
    private String defaultHost;
    private RequestAuth requestAuth;
    private HttpsConfig https;
    private boolean followProtocol;
    private SeoSwitch seoSwitch;

    /**
     * @return
     */
    public String getForm() {
        return form;
    }

    /**
     * @param form domain type
     */
    public void setForm(String form) {
        this.form = form;
    }

    /**
     * @return defaultHost
     */
    public String getDefaultHost() {
        return defaultHost;
    }

    /**
     * @param defaultHost default back source host
     */
    public void setDefaultHost(String defaultHost) {
        this.defaultHost = defaultHost;
    }

    /**
     * @return requestAuth
     */
    public RequestAuth getRequestAuth() {
        return requestAuth;
    }

    /**
     * @param requestAuth Access authentication configuration
     */
    public void setRequestAuth(RequestAuth requestAuth) {
        this.requestAuth = requestAuth;
    }

    /**
     * @return https
     */
    public HttpsConfig getHttps() {
        return https;
    }

    /**
     * @param https HTTPS acceleration configuration
     */
    public void setHttps(HttpsConfig https) {
        this.https = https;
    }

    /**
     * @return followProtocol
     */
    public boolean isFollowProtocol() {
        return followProtocol;
    }

    /**
     * @param followProtocol Whether the protocol is turned back to the source
     */
    public void setFollowProtocol(boolean followProtocol) {
        this.followProtocol = followProtocol;
    }

    /**
     * @return seoSwitch
     */
    public SeoSwitch getSeoSwitch() {
        return seoSwitch;
    }

    /**
     * @param seoSwitch seo switch config
     */
    public void setSeoSwitch(SeoSwitch seoSwitch) {
        this.seoSwitch = seoSwitch;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }
    
    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }
    
    /**
     * @return cname
     */
    public String getCname() {
        return cname;
    }
    
    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }
    
    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }
    
    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    /**
     * @return lastModifyTime
     */
    public String getLastModifyTime() {
        return lastModifyTime;
    }
    
    /**
     * @param lastModifyTime
     */
    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
    
    /**
     * @return if is baned
     */
    public boolean isBan() {
        return isBan;
    }

    /**
     * @param isBan
     */
    public void setBan(boolean isBan) {
        this.isBan = isBan;
    }

    /**
     * @return origin
     */
    public List<OriginPeer> getOrigin() {
        return origin;
    }
    
    /**
     * @param origin
     */
    public void setOrigin(List<OriginPeer> origin) {
        this.origin = origin;
    }
    
    /**
     * @return cacheTTL
     */
    public List<CacheTTL> getCacheTTL() {
        return cacheTTL;
    }
    
    /**
     * @param cacheTTL
     */
    public void setCacheTTL(List<CacheTTL> cacheTTL) {
        this.cacheTTL = cacheTTL;
    }
    
    /**
     * @return limitRate
     */
    public Integer getLimitRate() {
        return limitRate;
    }
    
    /**
     * @param limitRate
     */
    public void setLimitRate(Integer limitRate) {
        this.limitRate = limitRate;
    }
}
