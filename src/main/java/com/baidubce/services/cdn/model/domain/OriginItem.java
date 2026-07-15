package com.baidubce.services.cdn.model.domain;

/**
 * originConfig items.
 */
public class OriginItem {
    /**
     * The origin address.
     */
    private String addr;

    /**
     * The origin type.
     */
    private String type;

    /**
     * The http port. Defaults to 80.
     */
    private Integer httpPort;

    /**
     * The https port. Defaults to 443.
     */
    private Integer httpsPort;

    /**
     * The host value used when fetching from origin.
     */
    private String host;

    /**
     * The back-to-origin protocol.
     */
    private String upstreamProtocol;

    /**
     * The origin weight, range 1-100.
     */
    private Integer weight;

    /**
     * Whether this is a backup origin. true means backup, false means primary. Defaults to false.
     */
    private Boolean backup;

    /**
     * The ISP of the origin.
     */
    private String isp;

    /**
     * The third-party object storage authentication configuration.
     */
    private ThirdBucketAuth thirdBucketAuth;

    /**
     * The probe url for application-layer origin health check.
     */
    private String probeUrl;

    /**
     * @param addr
     * @return this object
     */
    public OriginItem withAddr(String addr) {
        this.addr = addr;
        return this;
    }

    /**
     * @return addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * @param type
     * @return this object
     */
    public OriginItem withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param httpPort
     * @return this object
     */
    public OriginItem withHttpPort(Integer httpPort) {
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
    public OriginItem withHttpsPort(Integer httpsPort) {
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
     * @param host
     * @return this object
     */
    public OriginItem withHost(String host) {
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
     * @param upstreamProtocol
     * @return this object
     */
    public OriginItem withUpstreamProtocol(String upstreamProtocol) {
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
     * @param weight
     * @return this object
     */
    public OriginItem withWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    /**
     * @return weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @param backup
     * @return this object
     */
    public OriginItem withBackup(Boolean backup) {
        this.backup = backup;
        return this;
    }

    /**
     * @return backup
     */
    public Boolean getBackup() {
        return backup;
    }

    /**
     * @param backup
     */
    public void setBackup(Boolean backup) {
        this.backup = backup;
    }

    /**
     * @param isp
     * @return this object
     */
    public OriginItem withIsp(String isp) {
        this.isp = isp;
        return this;
    }

    /**
     * @return isp
     */
    public String getIsp() {
        return isp;
    }

    /**
     * @param isp
     */
    public void setIsp(String isp) {
        this.isp = isp;
    }

    /**
     * @param thirdBucketAuth
     * @return this object
     */
    public OriginItem withThirdBucketAuth(ThirdBucketAuth thirdBucketAuth) {
        this.thirdBucketAuth = thirdBucketAuth;
        return this;
    }

    /**
     * @return thirdBucketAuth
     */
    public ThirdBucketAuth getThirdBucketAuth() {
        return thirdBucketAuth;
    }

    /**
     * @param thirdBucketAuth
     */
    public void setThirdBucketAuth(ThirdBucketAuth thirdBucketAuth) {
        this.thirdBucketAuth = thirdBucketAuth;
    }

    /**
     * @param probeUrl
     * @return this object
     */
    public OriginItem withProbeUrl(String probeUrl) {
        this.probeUrl = probeUrl;
        return this;
    }

    /**
     * @return probeUrl
     */
    public String getProbeUrl() {
        return probeUrl;
    }

    /**
     * @param probeUrl
     */
    public void setProbeUrl(String probeUrl) {
        this.probeUrl = probeUrl;
    }
}
