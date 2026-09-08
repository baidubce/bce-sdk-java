package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * A single origin server item of a rule engine origin configuration.
 *
 */
public class OriginConfig extends JsonObject {
    /**
     * The origin address. Supports IP or domain name.
     */
    private String addr;

    /**
     * The origin type.
     */
    private String type;

    /**
     * The http back-to-origin port.
     */
    private Integer httpPort;

    /**
     * The https back-to-origin port.
     */
    private Integer httpsPort;

    /**
     * The back-to-origin protocol.
     */
    private String upstreamProtocol;

    /**
     * The host value used when fetching from origin.
     */
    private String host;

    /**
     * The object storage authentication configuration of the origin.
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
}
