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
import com.baidubce.services.cdn.model.CdnRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yixing
 * update by changxing01 on 19/8/28
 */
public class SetDomainCacheFullUrlRequest extends CdnRequest {
    private String domain;

    /**
     * true和false，true表示支持全URL缓存，false表示忽略参数缓存(可保留部分参数)
     * 必选
     */
    private Boolean cacheFullUrl;

    /**
     * cacheFullUrl为true时，此项不起作用；cacheFullUrl为false时，此项表示保留的参数列表，如果为空，表示忽略所有参数
     * 可选
     */
    private List<String> cacheUrlArgs;

    /**
     * ignoreUrlArgs为true时，此项不起作用; cacheFullUrl为false时，此项表示忽略的参数列表，如果为空，表示保留所有参数
     * 可选
     */
    private List<String> ignoreUrlArgs;

    /**
     * @param ignoreUrlArgs List of reserved parameters
     * @return returns this object
     */
    public SetDomainCacheFullUrlRequest withIgnoreUrlArgs(List<String> ignoreUrlArgs) {
        this.ignoreUrlArgs = ignoreUrlArgs;
        return this;
    }

    /**
     * @param ignoreUrlArg List of reserved parameter
     * @return returns this object
     */
    public SetDomainCacheFullUrlRequest addIgnoreUrlArgs(String ignoreUrlArg) {
        if (this.ignoreUrlArgs == null) {
            this.ignoreUrlArgs = new ArrayList<String>();
        }
        this.ignoreUrlArgs.add(ignoreUrlArg);
        return this;
    }

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

    public Boolean getCacheFullUrl() {
        return cacheFullUrl;
    }

    public void setCacheFullUrl(Boolean cacheFullUrl) {
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

    public List<String> getIgnoreUrlArgs() {
        return ignoreUrlArgs;
    }

    public void setIgnoreUrlArgs(List<String> ignoreUrlArgs) {
        this.ignoreUrlArgs = ignoreUrlArgs;
    }
}

