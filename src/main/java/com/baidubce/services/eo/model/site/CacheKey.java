package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * The cache key (query string) configuration of a site.
 *
 * <p>Note: {@code exclude_args} and {@code include_args} can NOT be set at the same time,
 * and both only take effect when {@code query} is false.</p>
 */
public class CacheKey extends JsonObject {
    /**
     * Whether to keep the query args in the cache key. true keeps all args, false ignores them.
     */
    private Boolean query;

    /**
     * The args to be excluded from the cache key. Only effective when query is false.
     */
    @JsonProperty("exclude_args")
    private List<String> excludeArgs;

    /**
     * The args to be included in the cache key. Only effective when query is false.
     */
    @JsonProperty("include_args")
    private List<String> includeArgs;

    /**
     * @param query
     * @return this object
     */
    public CacheKey withQuery(Boolean query) {
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
     * @param excludeArgs
     * @return this object
     */
    public CacheKey withExcludeArgs(List<String> excludeArgs) {
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
    public CacheKey withIncludeArgs(List<String> includeArgs) {
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
}
