package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The cache key configuration used inside a rule engine config.
 *
 */
public class RuleCacheKey extends JsonObject {
    /**
     * Whether to keep the query args in the cache key.
     */
    private Boolean query;

    /**
     * Whether to ignore case.
     */
    @JsonProperty("ignore_case")
    private Boolean ignoreCase;

    /**
     * The args to be excluded from the cache key.
     */
    @JsonProperty("exclude_args")
    private List<String> excludeArgs;

    /**
     * The args to be included in the cache key.
     */
    @JsonProperty("include_args")
    private List<String> includeArgs;

    /**
     * The HTTP request header names to be used in the cache key.
     */
    private List<String> headers;

    /**
     * The cookie args to be used in the cache key.
     */
    private List<String> cookie;

    /**
     * @param query
     * @return this object
     */
    public RuleCacheKey withQuery(Boolean query) {
        this.query = query;
        return this;
    }

    /**
     * @return query
     */
    public Boolean getQuery() {
        return query;
    }

    /**
     * @param query
     */
    public void setQuery(Boolean query) {
        this.query = query;
    }

    /**
     * @param ignoreCase
     * @return this object
     */
    public RuleCacheKey withIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    /**
     * @return ignoreCase
     */
    public Boolean getIgnoreCase() {
        return ignoreCase;
    }

    /**
     * @param ignoreCase
     */
    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    /**
     * @param excludeArgs
     * @return this object
     */
    public RuleCacheKey withExcludeArgs(List<String> excludeArgs) {
        this.excludeArgs = excludeArgs;
        return this;
    }

    /**
     * @return excludeArgs
     */
    public List<String> getExcludeArgs() {
        return excludeArgs;
    }

    /**
     * @param excludeArgs
     */
    public void setExcludeArgs(List<String> excludeArgs) {
        this.excludeArgs = excludeArgs;
    }

    /**
     * @param includeArgs
     * @return this object
     */
    public RuleCacheKey withIncludeArgs(List<String> includeArgs) {
        this.includeArgs = includeArgs;
        return this;
    }

    /**
     * @return includeArgs
     */
    public List<String> getIncludeArgs() {
        return includeArgs;
    }

    /**
     * @param includeArgs
     */
    public void setIncludeArgs(List<String> includeArgs) {
        this.includeArgs = includeArgs;
    }

    /**
     * @param headers
     * @return this object
     */
    public RuleCacheKey withHeaders(List<String> headers) {
        this.headers = headers;
        return this;
    }

    /**
     * @return headers
     */
    public List<String> getHeaders() {
        return headers;
    }

    /**
     * @param headers
     */
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    /**
     * @param cookie
     * @return this object
     */
    public RuleCacheKey withCookie(List<String> cookie) {
        this.cookie = cookie;
        return this;
    }

    /**
     * @return cookie
     */
    public List<String> getCookie() {
        return cookie;
    }

    /**
     * @param cookie
     */
    public void setCookie(List<String> cookie) {
        this.cookie = cookie;
    }
}
