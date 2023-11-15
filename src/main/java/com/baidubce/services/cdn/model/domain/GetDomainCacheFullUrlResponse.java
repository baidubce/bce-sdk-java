package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

/**
 * create by changxing01 on 19/8/27
 */
public class GetDomainCacheFullUrlResponse extends CdnResponse {
    private boolean cacheFullUrl;
    private List<String> cacheUrlArgs;
    private List<String> ignoreUrlArgs;

    /**
     * @return cacheFullUrl
     */
    public boolean isCacheFullUrl() {
        return cacheFullUrl;
    }

    /**
     * @param cacheFullUrl whether full URL caching is supported
     */
    public void setCacheFullUrl(boolean cacheFullUrl) {
        this.cacheFullUrl = cacheFullUrl;
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

    public List<String> getIgnoreUrlArgs() {
        return ignoreUrlArgs;
    }

    public void setIgnoreUrlArgs(List<String> ignoreUrlArgs) {
        this.ignoreUrlArgs = ignoreUrlArgs;
    }
}
