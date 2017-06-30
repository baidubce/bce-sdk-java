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

import java.util.ArrayList;
import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author yixing
 *
 */
public class SetDomainCacheTTLRequest extends AbstractBceRequest {
    private String domain;
    private List<CacheTTL> cacheTTL;
    
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
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainCacheTTLRequest withDomain(String domain) {
        this.domain = domain;
        return this;
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
     * @param cacheTTL
     * @return returns this object
     */
    public SetDomainCacheTTLRequest withCacheTTL(List<CacheTTL> cacheTTL) {
        setCacheTTL(cacheTTL);
        return this;
    }
    
    /**
     * @param rule
     * @return returns this object
     */
    public SetDomainCacheTTLRequest addCacheTTL(CacheTTL rule) {
        if (cacheTTL == null) {
            cacheTTL = new ArrayList<CacheTTL>();
        }
        cacheTTL.add(rule);
        return this;
    }
    
    /**
     * (non-Javadoc)
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public SetDomainCacheTTLRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
    
    /**
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
