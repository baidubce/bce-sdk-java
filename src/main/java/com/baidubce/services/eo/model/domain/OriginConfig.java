package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

/**
 * A single origin configuration of an origin pool.
 */
public class OriginConfig extends JsonObject {
    /**
     * The origin address. For IP/DOMAIN it is an IP or domain; for object storage it is the BOS
     * bucket name or the S3 origin address. Must not be duplicated.
     */
    private String addr;

    /**
     * The origin type. Valid values are "IP", "DOMAIN", "BUCKET" (BOS uses "BUCKET", S3 uses
     * "DOMAIN").
     */
    private String type;

    /**
     * The host value used when fetching from origin.
     */
    private String host;

    /**
     * The upstream protocol. Valid values are "http", "https", "*".
     */
    private String upstreamProtocol;

    /**
     * The http origin port. Required when the upstream protocol is "*".
     */
    private Integer httpPort;

    /**
     * The https origin port. Required when the upstream protocol is "*".
     */
    private Integer httpsPort;

    /**
     * The origin weight, 1-100. Defaults to 50.
     */
    private Integer weight;

    /**
     * Whether this is a primary origin. It is always false (primary) in the query response.
     */
    private Boolean backup;

    /**
     * The object storage authentication configuration. Required for object storage origins.
     */
    private ThirdBucketAuth thirdBucketAuth;

    /**
     * @param addr
     * @return this object
     */
    public OriginConfig withAddr(String addr) {
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
    public OriginConfig withType(String type) {
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
     * @param host
     * @return this object
     */
    public OriginConfig withHost(String host) {
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
    public OriginConfig withUpstreamProtocol(String upstreamProtocol) {
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
     * @param httpPort
     * @return this object
     */
    public OriginConfig withHttpPort(Integer httpPort) {
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
    public OriginConfig withHttpsPort(Integer httpsPort) {
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
     * @param weight
     * @return this object
     */
    public OriginConfig withWeight(Integer weight) {
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
     * @param thirdBucketAuth
     * @return this object
     */
    public OriginConfig withThirdBucketAuth(ThirdBucketAuth thirdBucketAuth) {
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
     * @param backup
     * @return this object
     */
    public OriginConfig withBackup(Boolean backup) {
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
}
