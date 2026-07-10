package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;
import com.baidubce.services.eo.model.site.CacheTtl;
import com.baidubce.services.eo.model.site.Hsts;
import com.baidubce.services.eo.model.site.Http3;
import com.baidubce.services.eo.model.site.HttpHeader;

import java.util.List;

/**
 * The configuration items supported by a rule engine page rule.
 *
 */
public class Config extends JsonObject {
    /**
     * The node cache rules.
     */
    private List<CacheTtl> cacheTtl;

    /**
     * The cacheKey configuration of the rule.
     */
    private RuleCacheKey cacheKey;

    /**
     * The offline mode configuration.
     */
    private String offlineMode;

    /**
     * The force HTTPS redirect switch.
     */
    private String httpToHttpsEnabled;

    /**
     * The redirect status code of the force HTTPS config.
     */
    private String httpToHttpsCode;

    /**
     * The HSTS configuration.
     */
    private Hsts hsts;

    /**
     * The intelligent speed acceleration (ISA) config.
     */
    private String isa;

    /**
     * The HTTP2 configuration.
     */
    private String http2Disable;

    /**
     * The HTTP3 configuration.
     */
    private Http3 http3;

    /**
     * The HTTP2 origin config. Valid values are "ON" and "OFF".
     */
    private String http2Origin;

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
     * The custom HTTP header rules.
     */
    private List<HttpHeader> httpHeader;

    /**
     * The refresh revalidate (ignore client refresh) configuration.
     */
    private RefreshRevalidate refreshRevalidate;

    /**
     * The webSocket configuration.
     */
    private WebSocket webSocket;

    /**
     * The origin timeout configuration.
     */
    private OriginTimeout originTimeout;

    /**
     * The origin 301/302 redirect follow configuration.
     */
    private OriginRedirectOptions originRedirectOptions;

    /**
     * The origin range configuration.
     */
    private OriginOptions originOptions;

    /**
     * The real ip (client ip retrieval) configuration.
     */
    private RealIp realIp;

    /**
     * The custom error page configuration.
     */
    private List<ErrorPage> errorPage;

    /**
     * The single connection traffic limit configuration.
     */
    private TrafficLimit trafficLimit;

    /**
     * The URL authentication configuration.
     */
    private AntiHotLink antiHotLink;

    /**
     * The origin request args configuration.
     */
    private OriginArg originArg;

    /**
     * The access URL redirect rules configuration.
     */
    private List<UrlRules> urlRules;

    /**
     * @param cacheTtl
     * @return this object
     */
    public Config withCacheTtl(List<CacheTtl> cacheTtl) {
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
    public Config withCacheKey(RuleCacheKey cacheKey) {
        this.cacheKey = cacheKey;
        return this;
    }

    /**
     * @return cacheKey
     */
    public RuleCacheKey getCacheKey() {
        return cacheKey;
    }

    /**
     * @param cacheKey
     */
    public void setCacheKey(RuleCacheKey cacheKey) {
        this.cacheKey = cacheKey;
    }

    /**
     * @param offlineMode
     * @return this object
     */
    public Config withOfflineMode(String offlineMode) {
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
    public Config withHttpToHttpsEnabled(String httpToHttpsEnabled) {
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
    public Config withHttpToHttpsCode(String httpToHttpsCode) {
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
    public Config withHsts(Hsts hsts) {
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
     * @param isa
     * @return this object
     */
    public Config withIsa(String isa) {
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
     * @param http2Disable
     * @return this object
     */
    public Config withHttp2Disable(String http2Disable) {
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
    public Config withHttp3(Http3 http3) {
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
     * @param http2Origin
     * @return this object
     */
    public Config withHttp2Origin(String http2Origin) {
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
     * @param clientMaxBodySize
     * @return this object
     */
    public Config withClientMaxBodySize(String clientMaxBodySize) {
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
    public Config withCompress(String compress) {
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
    public Config withCompressMethodArray(List<String> compressMethodArray) {
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
     * @param httpHeader
     * @return this object
     */
    public Config withHttpHeader(List<HttpHeader> httpHeader) {
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
     * @param refreshRevalidate
     * @return this object
     */
    public Config withRefreshRevalidate(RefreshRevalidate refreshRevalidate) {
        this.refreshRevalidate = refreshRevalidate;
        return this;
    }

    /**
     * @return refreshRevalidate
     */
    public RefreshRevalidate getRefreshRevalidate() {
        return refreshRevalidate;
    }

    /**
     * @param refreshRevalidate
     */
    public void setRefreshRevalidate(RefreshRevalidate refreshRevalidate) {
        this.refreshRevalidate = refreshRevalidate;
    }

    /**
     * @param webSocket
     * @return this object
     */
    public Config withWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
        return this;
    }

    /**
     * @return webSocket
     */
    public WebSocket getWebSocket() {
        return webSocket;
    }

    /**
     * @param webSocket
     */
    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    /**
     * @param originTimeout
     * @return this object
     */
    public Config withOriginTimeout(OriginTimeout originTimeout) {
        this.originTimeout = originTimeout;
        return this;
    }

    /**
     * @return originTimeout
     */
    public OriginTimeout getOriginTimeout() {
        return originTimeout;
    }

    /**
     * @param originTimeout
     */
    public void setOriginTimeout(OriginTimeout originTimeout) {
        this.originTimeout = originTimeout;
    }

    /**
     * @param originRedirectOptions
     * @return this object
     */
    public Config withOriginRedirectOptions(OriginRedirectOptions originRedirectOptions) {
        this.originRedirectOptions = originRedirectOptions;
        return this;
    }

    /**
     * @return originRedirectOptions
     */
    public OriginRedirectOptions getOriginRedirectOptions() {
        return originRedirectOptions;
    }

    /**
     * @param originRedirectOptions
     */
    public void setOriginRedirectOptions(OriginRedirectOptions originRedirectOptions) {
        this.originRedirectOptions = originRedirectOptions;
    }

    /**
     * @param originOptions
     * @return this object
     */
    public Config withOriginOptions(OriginOptions originOptions) {
        this.originOptions = originOptions;
        return this;
    }

    /**
     * @return originOptions
     */
    public OriginOptions getOriginOptions() {
        return originOptions;
    }

    /**
     * @param originOptions
     */
    public void setOriginOptions(OriginOptions originOptions) {
        this.originOptions = originOptions;
    }

    /**
     * @param realIp
     * @return this object
     */
    public Config withRealIp(RealIp realIp) {
        this.realIp = realIp;
        return this;
    }

    /**
     * @return realIp
     */
    public RealIp getRealIp() {
        return realIp;
    }

    /**
     * @param realIp
     */
    public void setRealIp(RealIp realIp) {
        this.realIp = realIp;
    }

    /**
     * @param errorPage
     * @return this object
     */
    public Config withErrorPage(List<ErrorPage> errorPage) {
        this.errorPage = errorPage;
        return this;
    }

    /**
     * @return errorPage
     */
    public List<ErrorPage> getErrorPage() {
        return errorPage;
    }

    /**
     * @param errorPage
     */
    public void setErrorPage(List<ErrorPage> errorPage) {
        this.errorPage = errorPage;
    }

    /**
     * @param trafficLimit
     * @return this object
     */
    public Config withTrafficLimit(TrafficLimit trafficLimit) {
        this.trafficLimit = trafficLimit;
        return this;
    }

    /**
     * @return trafficLimit
     */
    public TrafficLimit getTrafficLimit() {
        return trafficLimit;
    }

    /**
     * @param trafficLimit
     */
    public void setTrafficLimit(TrafficLimit trafficLimit) {
        this.trafficLimit = trafficLimit;
    }

    /**
     * @param antiHotLink
     * @return this object
     */
    public Config withAntiHotLink(AntiHotLink antiHotLink) {
        this.antiHotLink = antiHotLink;
        return this;
    }

    /**
     * @return antiHotLink
     */
    public AntiHotLink getAntiHotLink() {
        return antiHotLink;
    }

    /**
     * @param antiHotLink
     */
    public void setAntiHotLink(AntiHotLink antiHotLink) {
        this.antiHotLink = antiHotLink;
    }

    /**
     * @param originArg
     * @return this object
     */
    public Config withOriginArg(OriginArg originArg) {
        this.originArg = originArg;
        return this;
    }

    /**
     * @return originArg
     */
    public OriginArg getOriginArg() {
        return originArg;
    }

    /**
     * @param originArg
     */
    public void setOriginArg(OriginArg originArg) {
        this.originArg = originArg;
    }

    /**
     * @param urlRules
     * @return this object
     */
    public Config withUrlRules(List<UrlRules> urlRules) {
        this.urlRules = urlRules;
        return this;
    }

    /**
     * @return urlRules
     */
    public List<UrlRules> getUrlRules() {
        return urlRules;
    }

    /**
     * @param urlRules
     */
    public void setUrlRules(List<UrlRules> urlRules) {
        this.urlRules = urlRules;
    }
}
