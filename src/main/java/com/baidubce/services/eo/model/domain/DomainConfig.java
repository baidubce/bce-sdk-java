package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * The detailed configuration of an accelerate domain.
 */
public class DomainConfig extends JsonObject {
    /**
     * The accelerate domain.
     */
    private String domain;

    /**
     * The domain status.
     */
    private String status;

    /**
     * The https switch. "ON" to enable, "OFF" to disable.
     */
    private String httpsEnabled;

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
     * The certificate id.
     */
    private String certId;

    /**
     * @param domain
     * @return this object
     */
    public DomainConfig withDomain(String domain) {
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
     * @param status
     * @return this object
     */
    public DomainConfig withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param httpsEnabled
     * @return this object
     */
    public DomainConfig withHttpsEnabled(String httpsEnabled) {
        this.httpsEnabled = httpsEnabled;
        return this;
    }

    /**
     * @return httpsEnabled
     */
    public String getHttpsEnabled() {
        return httpsEnabled;
    }

    /**
     * @param httpsEnabled
     */
    public void setHttpsEnabled(String httpsEnabled) {
        this.httpsEnabled = httpsEnabled;
    }

    /**
     * @param originConfig
     * @return this object
     */
    public DomainConfig withOriginConfig(List<OriginConfig> originConfig) {
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
    public DomainConfig withOriginPoolId(String originPoolId) {
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
    public DomainConfig withUpstreamProtocol(String upstreamProtocol) {
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
    public DomainConfig withIpv6Dispatch(Ipv6Dispatch ipv6Dispatch) {
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
    public DomainConfig withHttpPort(Integer httpPort) {
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
    public DomainConfig withHttpsPort(Integer httpsPort) {
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
    public DomainConfig withLoadBalanceId(String loadBalanceId) {
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

    /**
     * @param certId
     * @return this object
     */
    public DomainConfig withCertId(String certId) {
        this.certId = certId;
        return this;
    }

    /**
     * @return certId
     */
    public String getCertId() {
        return certId;
    }

    /**
     * @param certId
     */
    public void setCertId(String certId) {
        this.certId = certId;
    }
}
