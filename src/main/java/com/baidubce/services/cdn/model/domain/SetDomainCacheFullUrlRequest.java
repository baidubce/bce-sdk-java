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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yixing
 * update by changxing01 on 19/8/28
 */
public class SetDomainCacheFullUrlRequest extends AbstractBceRequest {
    private String domain;
    private boolean cacheFullUrl;
    private List<String> cacheUrlArgs;

    /**
     * @return cacheUrlArgs
     */
    public List<String> getCacheUrlArgs() {
        return cacheUrlArgs;
    }

    /**
     * @param cacheUrlArgs List of reserved parameters
     */
    public void setCacheUrlArgs(List<String> cacheUrlArgs) {
        this.cacheUrlArgs = cacheUrlArgs;
    }

    /**
     * @param cacheUrlArgs List of reserved parameters
     * @return returns this object
     */
    public SetDomainCacheFullUrlRequest withCacheUrlArgs(List<String> cacheUrlArgs) {
        this.cacheUrlArgs = cacheUrlArgs;
        return this;
    }

    /**
     * @param cacheUrlArg List of reserved parameter
     * @return returns this object
     */
    public SetDomainCacheFullUrlRequest addCacheUrlArgs(String cacheUrlArg) {
        if (this.cacheUrlArgs == null) {
            this.cacheUrlArgs = new ArrayList<String>();
        }
        this.cacheUrlArgs.add(cacheUrlArg);
        return this;
    }

    /**
     * @return cacheFullUrl
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
     * @param cacheFullUrl
     * @return this object
     */
    public SetDomainCacheFullUrlRequest withCacheFullUrl(boolean cacheFullUrl) {
        this.cacheFullUrl = cacheFullUrl;
        return this;
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
     * @param domain the domain name
     * @return returns this object
     */
    public SetDomainCacheFullUrlRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }
    
    /**
     * (non-Javadoc)
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public SetDomainCacheFullUrlRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * (non-Javadoc)
     * @see Object#toString()
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

