package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

import java.util.List;

/**
 * The request for creating an accelerate domain.
 */
public class CreateDomainRequest extends EoRequest {
    /**
     * The accelerate domain.
     */
    private String domain;

    /**
     * The site id.
     */
    private Integer siteId;

    /**
     * The origin configurations.
     */
    private List<OriginConfig> originConfig;

    /**
     * The origin pool id. Means the domain uses an origin pool as its origin.
     */
    private String originPoolId;

    /**
     * The upstream protocol. Valid values are "http", "https", "*".
     */
    private String upstreamProtocol;

    /**
     * The ipv6 dispatch configuration.
     */
    private Ipv6Dispatch ipv6Dispatch;

    /**
     * The http origin port.
     */
    private Integer httpPort;

    /**
     * The https origin port.
     */
    private Integer httpsPort;

    /**
     * The load balance id. Means the domain uses a load balance as its origin.
     */
    private String loadBalanceId;

    /**
     * @param domain
     * @return this object
     */
    public CreateDomainRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param siteId
     * @return this object
     */
    public CreateDomainRequest withSiteId(Integer siteId) {
        this.siteId = siteId;
        return this;
    }

    /**
     * @return siteId
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * @param siteId
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * @param originConfig
     * @return this object
     */
    public CreateDomainRequest withOriginConfig(List<OriginConfig> originConfig) {
        this.originConfig = originConfig;
        return this;
    }

    /**
     * @return originConfig
     */
    public List<OriginConfig> getOriginConfig() {
        return originConfig;
    }

    /**
     * @param originConfig
     */
    public void setOriginConfig(List<OriginConfig> originConfig) {
        this.originConfig = originConfig;
    }

    /**
     * @param originPoolId
     * @return this object
     */
    public CreateDomainRequest withOriginPoolId(String originPoolId) {
        this.originPoolId = originPoolId;
        return this;
    }

    /**
     * @return originPoolId
     */
    public String getOriginPoolId() {
        return originPoolId;
    }

    /**
     * @param originPoolId
     */
    public void setOriginPoolId(String originPoolId) {
        this.originPoolId = originPoolId;
    }

    /**
     * @param upstreamProtocol
     * @return this object
     */
    public CreateDomainRequest withUpstreamProtocol(String upstreamProtocol) {
        this.upstreamProtocol = upstreamProtocol;
        return this;
    }

    /**
     * @return upstreamProtocol
     */
    public String getUpstreamProtocol() {
        return upstreamProtocol;
    }

    /**
     * @param upstreamProtocol
     */
    public void setUpstreamProtocol(String upstreamProtocol) {
        this.upstreamProtocol = upstreamProtocol;
    }

    /**
     * @param ipv6Dispatch
     * @return this object
     */
    public CreateDomainRequest withIpv6Dispatch(Ipv6Dispatch ipv6Dispatch) {
        this.ipv6Dispatch = ipv6Dispatch;
        return this;
    }

    /**
     * @return ipv6Dispatch
     */
    public Ipv6Dispatch getIpv6Dispatch() {
        return ipv6Dispatch;
    }

    /**
     * @param ipv6Dispatch
     */
    public void setIpv6Dispatch(Ipv6Dispatch ipv6Dispatch) {
        this.ipv6Dispatch = ipv6Dispatch;
    }

    /**
     * @param httpPort
     * @return this object
     */
    public CreateDomainRequest withHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
        return this;
    }

    /**
     * @return httpPort
     */
    public Integer getHttpPort() {
        return httpPort;
    }

    /**
     * @param httpPort
     */
    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
    }

    /**
     * @param httpsPort
     * @return this object
     */
    public CreateDomainRequest withHttpsPort(Integer httpsPort) {
        this.httpsPort = httpsPort;
        return this;
    }

    /**
     * @return httpsPort
     */
    public Integer getHttpsPort() {
        return httpsPort;
    }

    /**
     * @param httpsPort
     */
    public void setHttpsPort(Integer httpsPort) {
        this.httpsPort = httpsPort;
    }

    /**
     * @param loadBalanceId
     * @return this object
     */
    public CreateDomainRequest withLoadBalanceId(String loadBalanceId) {
        this.loadBalanceId = loadBalanceId;
        return this;
    }

    /**
     * @return loadBalanceId
     */
    public String getLoadBalanceId() {
        return loadBalanceId;
    }

    /**
     * @param loadBalanceId
     */
    public void setLoadBalanceId(String loadBalanceId) {
        this.loadBalanceId = loadBalanceId;
    }
}
