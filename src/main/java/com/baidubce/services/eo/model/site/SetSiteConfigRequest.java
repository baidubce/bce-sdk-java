package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoRequest;
import com.baidubce.services.eo.model.rule.PageRule;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The request for setting the node configuration of a site.
 *
 */
public class SetSiteConfigRequest extends EoRequest {
    /**
     * The site to be configured.
     */
    @JsonIgnore
    private String site;

    /**
     * The node cache rules of the site.
     */
    private List<CacheTtl> cacheTtl;

    /**
     * The cacheKey configuration of the site.
     */
    private CacheKey cacheKey;

    /**
     * The offline mode configuration of the site.
     */
    private String offlineMode;

    /**
     * The force HTTPS redirect switch of the site.
     */
    private String httpToHttpsEnabled;

    /**
     * The redirect status code of the force HTTPS config.
     */
    private String httpToHttpsCode;

    /**
     * The HSTS configuration of the site.
     */
    private Hsts hsts;

    /**
     * The HTTP2 configuration of the site.
     */
    private String http2Disable;

    /**
     * The HTTP3 configuration of the site.
     */
    private Http3 http3;

    /**
     * The max upload body size config.
     */
    private String clientMaxBodySize;

    /**
     * The page compress switch.
     */
    private String compress;

    /**
     * The page compress methods.
     */
    private List<String> compressMethodArray;

    /**
     * The intelligent speed acceleration (ISA) config.
     */
    private String isa;

    /**
     * The custom HTTP header rules of the site.
     */
    private List<HttpHeader> httpHeader;

    /**
     * The status code cache rules of the site.
     */
    private List<CacheCodeTtl> cacheCodeTtl;

    /**
     * The gRPC origin config.
     */
    private String grpcOrigin;

    /**
     * The HTTP2 origin config.
     */
    private String http2Origin;

    /**
     * The rule engine (page rules) configuration of the site. Must be set in full amount,
     * otherwise the previous rules will be overwritten.
     */
    private List<PageRule> pageRules;

    /**
     * @param site
     * @return this object
     */
    public SetSiteConfigRequest withSite(String site) {
        this.site = site;
        return this;
    }

    /**
     * @return site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @param cacheTtl
     * @return this object
     */
    public SetSiteConfigRequest withCacheTtl(List<CacheTtl> cacheTtl) {
        this.cacheTtl = cacheTtl;
        return this;
    }

    /**
     * @return cacheTtl
     */
    public List<CacheTtl> getCacheTtl() {
        return cacheTtl;
    }

    /**
     * @param cacheTtl
     */
    public void setCacheTtl(List<CacheTtl> cacheTtl) {
        this.cacheTtl = cacheTtl;
    }

    /**
     * @param cacheKey
     * @return this object
     */
    public SetSiteConfigRequest withCacheKey(CacheKey cacheKey) {
        this.cacheKey = cacheKey;
        return this;
    }

    /**
     * @return cacheKey
     */
    public CacheKey getCacheKey() {
        return cacheKey;
    }

    /**
     * @param cacheKey
     */
    public void setCacheKey(CacheKey cacheKey) {
        this.cacheKey = cacheKey;
    }

    /**
     * @param offlineMode
     * @return this object
     */
    public SetSiteConfigRequest withOfflineMode(String offlineMode) {
        this.offlineMode = offlineMode;
        return this;
    }

    /**
     * @return offlineMode
     */
    public String getOfflineMode() {
        return offlineMode;
    }

    /**
     * @param offlineMode
     */
    public void setOfflineMode(String offlineMode) {
        this.offlineMode = offlineMode;
    }

    /**
     * @param httpToHttpsEnabled
     * @return this object
     */
    public SetSiteConfigRequest withHttpToHttpsEnabled(String httpToHttpsEnabled) {
        this.httpToHttpsEnabled = httpToHttpsEnabled;
        return this;
    }

    /**
     * @return httpToHttpsEnabled
     */
    public String getHttpToHttpsEnabled() {
        return httpToHttpsEnabled;
    }

    /**
     * @param httpToHttpsEnabled
     */
    public void setHttpToHttpsEnabled(String httpToHttpsEnabled) {
        this.httpToHttpsEnabled = httpToHttpsEnabled;
    }

    /**
     * @param httpToHttpsCode
     * @return this object
     */
    public SetSiteConfigRequest withHttpToHttpsCode(String httpToHttpsCode) {
        this.httpToHttpsCode = httpToHttpsCode;
        return this;
    }

    /**
     * @return httpToHttpsCode
     */
    public String getHttpToHttpsCode() {
        return httpToHttpsCode;
    }

    /**
     * @param httpToHttpsCode
     */
    public void setHttpToHttpsCode(String httpToHttpsCode) {
        this.httpToHttpsCode = httpToHttpsCode;
    }

    /**
     * @param hsts
     * @return this object
     */
    public SetSiteConfigRequest withHsts(Hsts hsts) {
        this.hsts = hsts;
        return this;
    }

    /**
     * @return hsts
     */
    public Hsts getHsts() {
        return hsts;
    }

    /**
     * @param hsts
     */
    public void setHsts(Hsts hsts) {
        this.hsts = hsts;
    }

    /**
     * @param http2Disable
     * @return this object
     */
    public SetSiteConfigRequest withHttp2Disable(String http2Disable) {
        this.http2Disable = http2Disable;
        return this;
    }

    /**
     * @return http2Disable
     */
    public String getHttp2Disable() {
        return http2Disable;
    }

    /**
     * @param http2Disable
     */
    public void setHttp2Disable(String http2Disable) {
        this.http2Disable = http2Disable;
    }

    /**
     * @param http3
     * @return this object
     */
    public SetSiteConfigRequest withHttp3(Http3 http3) {
        this.http3 = http3;
        return this;
    }

    /**
     * @return http3
     */
    public Http3 getHttp3() {
        return http3;
    }

    /**
     * @param http3
     */
    public void setHttp3(Http3 http3) {
        this.http3 = http3;
    }

    /**
     * @param clientMaxBodySize
     * @return this object
     */
    public SetSiteConfigRequest withClientMaxBodySize(String clientMaxBodySize) {
        this.clientMaxBodySize = clientMaxBodySize;
        return this;
    }

    /**
     * @return clientMaxBodySize
     */
    public String getClientMaxBodySize() {
        return clientMaxBodySize;
    }

    /**
     * @param clientMaxBodySize
     */
    public void setClientMaxBodySize(String clientMaxBodySize) {
        this.clientMaxBodySize = clientMaxBodySize;
    }

    /**
     * @param compress
     * @return this object
     */
    public SetSiteConfigRequest withCompress(String compress) {
        this.compress = compress;
        return this;
    }

    /**
     * @return compress
     */
    public String getCompress() {
        return compress;
    }

    /**
     * @param compress
     */
    public void setCompress(String compress) {
        this.compress = compress;
    }

    /**
     * @param compressMethodArray
     * @return this object
     */
    public SetSiteConfigRequest withCompressMethodArray(List<String> compressMethodArray) {
        this.compressMethodArray = compressMethodArray;
        return this;
    }

    /**
     * @return compressMethodArray
     */
    public List<String> getCompressMethodArray() {
        return compressMethodArray;
    }

    /**
     * @param compressMethodArray
     */
    public void setCompressMethodArray(List<String> compressMethodArray) {
        this.compressMethodArray = compressMethodArray;
    }

    /**
     * @param isa
     * @return this object
     */
    public SetSiteConfigRequest withIsa(String isa) {
        this.isa = isa;
        return this;
    }

    /**
     * @return isa
     */
    public String getIsa() {
        return isa;
    }

    /**
     * @param isa
     */
    public void setIsa(String isa) {
        this.isa = isa;
    }

    /**
     * @param httpHeader
     * @return this object
     */
    public SetSiteConfigRequest withHttpHeader(List<HttpHeader> httpHeader) {
        this.httpHeader = httpHeader;
        return this;
    }

    /**
     * @return httpHeader
     */
    public List<HttpHeader> getHttpHeader() {
        return httpHeader;
    }

    /**
     * @param httpHeader
     */
    public void setHttpHeader(List<HttpHeader> httpHeader) {
        this.httpHeader = httpHeader;
    }

    /**
     * @param cacheCodeTtl
     * @return this object
     */
    public SetSiteConfigRequest withCacheCodeTtl(List<CacheCodeTtl> cacheCodeTtl) {
        this.cacheCodeTtl = cacheCodeTtl;
        return this;
    }

    /**
     * @return cacheCodeTtl
     */
    public List<CacheCodeTtl> getCacheCodeTtl() {
        return cacheCodeTtl;
    }

    /**
     * @param cacheCodeTtl
     */
    public void setCacheCodeTtl(List<CacheCodeTtl> cacheCodeTtl) {
        this.cacheCodeTtl = cacheCodeTtl;
    }

    /**
     * @param grpcOrigin
     * @return this object
     */
    public SetSiteConfigRequest withGrpcOrigin(String grpcOrigin) {
        this.grpcOrigin = grpcOrigin;
        return this;
    }

    /**
     * @return grpcOrigin
     */
    public String getGrpcOrigin() {
        return grpcOrigin;
    }

    /**
     * @param grpcOrigin
     */
    public void setGrpcOrigin(String grpcOrigin) {
        this.grpcOrigin = grpcOrigin;
    }

    /**
     * @param http2Origin
     * @return this object
     */
    public SetSiteConfigRequest withHttp2Origin(String http2Origin) {
        this.http2Origin = http2Origin;
        return this;
    }

    /**
     * @return http2Origin
     */
    public String getHttp2Origin() {
        return http2Origin;
    }

    /**
     * @param http2Origin
     */
    public void setHttp2Origin(String http2Origin) {
        this.http2Origin = http2Origin;
    }

    /**
     * @param pageRules
     * @return this object
     */
    public SetSiteConfigRequest withPageRules(List<PageRule> pageRules) {
        this.pageRules = pageRules;
        return this;
    }

    /**
     * @return pageRules
     */
    public List<PageRule> getPageRules() {
        return pageRules;
    }

    /**
     * @param pageRules
     */
    public void setPageRules(List<PageRule> pageRules) {
        this.pageRules = pageRules;
    }
}
