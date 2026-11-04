package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The health check (probe) configuration of a load balance.
 */
public class DetectConfig extends JsonObject {
    /**
     * The health check type. Valid values are "ICMP", "TCP", "UDP", "OFF". Not required for
     * HTTPS/HTTP health checks.
     */
    private String healthCheckType;

    /**
     * The probe interval, 10-3600, in seconds.
     */
    private Integer probeInterval;

    /**
     * The minimum number of probe successes, 1-5.
     */
    private Integer probeMinSuccesses;

    /**
     * The maximum number of probe failures, 1-5.
     */
    private Integer probeMaxFails;

    /**
     * The probe timeout, in seconds.
     */
    private Integer probeTimeout;

    /**
     * The expected status code. Required for HTTPS/HTTP health checks.
     */
    private String probeSucceedCode;

    /**
     * The probe path. Required for HTTPS/HTTP health checks.
     */
    private String probeUrl;

    /**
     * The probe request method. Required for HTTPS/HTTP health checks.
     */
    private String probeMethod;

    /**
     * The probe request content. Required for UDP health checks.
     */
    private String checkData;

    /**
     * The probe response content. Optional for UDP health checks.
     */
    private String checkResult;

    /**
     * @param healthCheckType
     * @return this object
     */
    public DetectConfig withHealthCheckType(String healthCheckType) {
        this.healthCheckType = healthCheckType;
        return this;
    }

    /**
     * @return healthCheckType
     */
    public String getHealthCheckType() {
        return healthCheckType;
    }

    /**
     * @param healthCheckType
     */
    public void setHealthCheckType(String healthCheckType) {
        this.healthCheckType = healthCheckType;
    }

    /**
     * @param probeInterval
     * @return this object
     */
    public DetectConfig withProbeInterval(Integer probeInterval) {
        this.probeInterval = probeInterval;
        return this;
    }

    /**
     * @return probeInterval
     */
    public Integer getProbeInterval() {
        return probeInterval;
    }

    /**
     * @param probeInterval
     */
    public void setProbeInterval(Integer probeInterval) {
        this.probeInterval = probeInterval;
    }

    /**
     * @param probeMinSuccesses
     * @return this object
     */
    public DetectConfig withProbeMinSuccesses(Integer probeMinSuccesses) {
        this.probeMinSuccesses = probeMinSuccesses;
        return this;
    }

    /**
     * @return probeMinSuccesses
     */
    public Integer getProbeMinSuccesses() {
        return probeMinSuccesses;
    }

    /**
     * @param probeMinSuccesses
     */
    public void setProbeMinSuccesses(Integer probeMinSuccesses) {
        this.probeMinSuccesses = probeMinSuccesses;
    }

    /**
     * @param probeMaxFails
     * @return this object
     */
    public DetectConfig withProbeMaxFails(Integer probeMaxFails) {
        this.probeMaxFails = probeMaxFails;
        return this;
    }

    /**
     * @return probeMaxFails
     */
    public Integer getProbeMaxFails() {
        return probeMaxFails;
    }

    /**
     * @param probeMaxFails
     */
    public void setProbeMaxFails(Integer probeMaxFails) {
        this.probeMaxFails = probeMaxFails;
    }

    /**
     * @param probeTimeout
     * @return this object
     */
    public DetectConfig withProbeTimeout(Integer probeTimeout) {
        this.probeTimeout = probeTimeout;
        return this;
    }

    /**
     * @return probeTimeout
     */
    public Integer getProbeTimeout() {
        return probeTimeout;
    }

    /**
     * @param probeTimeout
     */
    public void setProbeTimeout(Integer probeTimeout) {
        this.probeTimeout = probeTimeout;
    }

    /**
     * @param probeSucceedCode
     * @return this object
     */
    public DetectConfig withProbeSucceedCode(String probeSucceedCode) {
        this.probeSucceedCode = probeSucceedCode;
        return this;
    }

    /**
     * @return probeSucceedCode
     */
    public String getProbeSucceedCode() {
        return probeSucceedCode;
    }

    /**
     * @param probeSucceedCode
     */
    public void setProbeSucceedCode(String probeSucceedCode) {
        this.probeSucceedCode = probeSucceedCode;
    }

    /**
     * @param probeUrl
     * @return this object
     */
    public DetectConfig withProbeUrl(String probeUrl) {
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

    /**
     * @param probeMethod
     * @return this object
     */
    public DetectConfig withProbeMethod(String probeMethod) {
        this.probeMethod = probeMethod;
        return this;
    }

    /**
     * @return probeMethod
     */
    public String getProbeMethod() {
        return probeMethod;
    }

    /**
     * @param probeMethod
     */
    public void setProbeMethod(String probeMethod) {
        this.probeMethod = probeMethod;
    }

    /**
     * @param checkData
     * @return this object
     */
    public DetectConfig withCheckData(String checkData) {
        this.checkData = checkData;
        return this;
    }

    /**
     * @return checkData
     */
    public String getCheckData() {
        return checkData;
    }

    /**
     * @param checkData
     */
    public void setCheckData(String checkData) {
        this.checkData = checkData;
    }

    /**
     * @param checkResult
     * @return this object
     */
    public DetectConfig withCheckResult(String checkResult) {
        this.checkResult = checkResult;
        return this;
    }

    /**
     * @return checkResult
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * @param checkResult
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
}
