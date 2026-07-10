package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoResponse;
import com.baidubce.services.eo.model.rule.PageRule;

import java.util.List;

/**
 * The response of querying configuration of a site.
 *
 * This interface returns all configuration items of the site. New items (e.g. cacheKey,
 * h2, h3, etc.) can be added as fields as they are supported.
 */
public class GetSiteConfigResponse extends EoResponse {
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
     * The rule engine (page rules) configuration of the site.
     */
    private List<PageRule> pageRules;

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
