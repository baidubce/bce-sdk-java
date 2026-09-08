package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The origin pool configuration of a rule engine config.
 */
public class OriginPool extends JsonObject {
    /**
     * The id of the origin pool.
     */
    private String originPoolId;

    /**
     * The back-to-origin protocol. Valid values are "http", "https", "*".
     */
    private String upstreamProtocol;

    /**
     * The http back-to-origin port.
     */
    private Integer httpPort;

    /**
     * The https back-to-origin port.
     */
    private Integer httpsPort;

    /**
     * @param originPoolId
     * @return this object
     */
    public OriginPool withOriginPoolId(String originPoolId) {
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
    public OriginPool withUpstreamProtocol(String upstreamProtocol) {
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
    public OriginPool withHttpPort(Integer httpPort) {
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
    public OriginPool withHttpsPort(Integer httpsPort) {
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
}
