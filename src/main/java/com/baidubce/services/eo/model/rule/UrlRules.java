package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The access URL redirect rule configuration.
 */
public class UrlRules extends JsonObject {
    /**
     * The request scheme.
     */
    private String scheme;

    /**
     * The redirect host.
     */
    private String host;

    /**
     * The path to match the request.
     */
    private String srcPath;

    /**
     * The redirect target path.
     */
    private String dstPath;

    /**
     * Whether to carry the original request args.
     */
    private String query;

    /**
     * The redirect status code.
     */
    private Integer status;

    /**
     * @param scheme
     * @return this object
     */
    public UrlRules withScheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    /**
     * @return scheme
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * @param scheme
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * @param host
     * @return this object
     */
    public UrlRules withHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @param srcPath
     * @return this object
     */
    public UrlRules withSrcPath(String srcPath) {
        this.srcPath = srcPath;
        return this;
    }

    /**
     * @return srcPath
     */
    public String getSrcPath() {
        return srcPath;
    }

    /**
     * @param srcPath
     */
    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    /**
     * @param dstPath
     * @return this object
     */
    public UrlRules withDstPath(String dstPath) {
        this.dstPath = dstPath;
        return this;
    }

    /**
     * @return dstPath
     */
    public String getDstPath() {
        return dstPath;
    }

    /**
     * @param dstPath
     */
    public void setDstPath(String dstPath) {
        this.dstPath = dstPath;
    }

    /**
     * @param query
     * @return this object
     */
    public UrlRules withQuery(String query) {
        this.query = query;
        return this;
    }

    /**
     * @return query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @param status
     * @return this object
     */
    public UrlRules withStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
