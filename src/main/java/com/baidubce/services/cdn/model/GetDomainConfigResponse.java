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

import java.util.Date;
import java.util.List;

/**
 * @author yixing
 *
 */
public class GetDomainConfigResponse extends CdnResponse {
    private String domain;
    private String cname;
    private String status;
    private Date createTime;
    private Date lastModifyTime;
    private boolean isBan;
    private boolean cacheFullUrl;
    private List<OriginPeer> origin;
    private RefererACL refererACL;
    private IpACL ipACL;
    private List<CacheTTL> cacheTTL;
    private Integer limitRate;
    
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
    public Date getCreateTime() {
        return createTime;
    }
    
    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * @return lastModifyTime
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }
    
    /**
     * @param lastModifyTime
     */
    public void setLastModifyTime(Date lastModifyTime) {
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
     * @return if cache full url
     */
    public boolean isCacheFullUrl() {
        return cacheFullUrl;
    }

    /**
     * @param cacheFullUrl
     */
    public void setCacheFullUrl(boolean cacheFullUrl) {
        this.cacheFullUrl = cacheFullUrl;
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
     * @return ipACL
     */
    public IpACL getIpACL() {
        return ipACL;
    }

    /**
     * @param ipACL
     */
    public void setIpACL(IpACL ipACL) {
        this.ipACL = ipACL;
    }
    
    /**
     * @return refererACL
     */
    public RefererACL getRefererACL() {
        return refererACL;
    }

    /**
     * @param refererACL
     */
    public void setRefererACL(RefererACL refererACL) {
        this.refererACL = refererACL;
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
