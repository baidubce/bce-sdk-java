package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * The accelerate domain returned by the domain list query.
 */
public class Domain extends JsonObject {
    /**
     * The accelerate domain.
     */
    private String domain;

    /**
     * The site id.
     */
    private Integer siteId;

    /**
     * The cname information.
     */
    private String cname;

    /**
     * The cname verification status.
     */
    private Integer cnameStatus;

    /**
     * The domain status.
     */
    private String status;

    /**
     * The ban status. Valid values are "ADMIN", "CLOSURE", "USER".
     */
    private String banStatus;

    /**
     * The https switch. "ON" to enable, "OFF" to disable.
     */
    private String httpsEnabled;

    /**
     * The origin configurations. Null when the domain uses a load balance.
     */
    private List<OriginConfig> originConfig;

    /**
     * The origin address, origin pool name or load balance name.
     */
    private String originInformation;

    /**
     * The origin type.
     */
    private String originType;

    /**
     * The load balance id.
     */
    private String loadBalanceId;

    /**
     * The origin pool id.
     */
    private String originPoolId;

    /**
     * The origin configuration when the domain uses a load balance.
     */
    private OriginPools originPools;

    /**
     * The ipv6 dispatch configuration.
     */
    private Ipv6Dispatch ipv6Dispatch;

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
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return cnameStatus
     */
    public Integer getCnameStatus() {
        return cnameStatus;
    }

    /**
     * @param cnameStatus
     */
    public void setCnameStatus(Integer cnameStatus) {
        this.cnameStatus = cnameStatus;
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
     * @return banStatus
     */
    public String getBanStatus() {
        return banStatus;
    }

    /**
     * @param banStatus
     */
    public void setBanStatus(String banStatus) {
        this.banStatus = banStatus;
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
     * @return originInformation
     */
    public String getOriginInformation() {
        return originInformation;
    }

    /**
     * @param originInformation
     */
    public void setOriginInformation(String originInformation) {
        this.originInformation = originInformation;
    }

    /**
     * @return originType
     */
    public String getOriginType() {
        return originType;
    }

    /**
     * @param originType
     */
    public void setOriginType(String originType) {
        this.originType = originType;
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
     * @return originPools
     */
    public OriginPools getOriginPools() {
        return originPools;
    }

    /**
     * @param originPools
     */
    public void setOriginPools(OriginPools originPools) {
        this.originPools = originPools;
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
}
