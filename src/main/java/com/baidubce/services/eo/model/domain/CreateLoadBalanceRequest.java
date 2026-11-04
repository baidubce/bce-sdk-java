package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.EoRequest;

import java.util.List;

/**
 * The request for creating a load balance of a site.
 */
public class CreateLoadBalanceRequest extends EoRequest {
    /**
     * The site id that the load balance belongs to.
     */
    private Integer siteId;

    /**
     * The load balance name.
     */
    private String name;

    /**
     * The load balance strategy. Valid values are "priority_mode" (primary-backup) and
     * "weight_mode" (weighted round robin).
     */
    private String dispatchMode;

    /**
     * The session persistence. Valid values are "IP" and "OFF".
     */
    private String sessionMode;

    /**
     * The health check (probe) configuration.
     */
    private DetectConfig detectConfig;

    /**
     * The origin pool ids used by the load balance.
     */
    private List<String> originPoolIds;

    /**
     * The origin pool weights, required when the strategy is weighted round robin.
     */
    private List<Integer> weights;

    /**
     * The fallback origin pool id. Omitted when not set.
     */
    private String fallbackOriginPoolId;

    /**
     * @param siteId
     * @return this object
     */
    public CreateLoadBalanceRequest withSiteId(Integer siteId) {
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
     * @param name
     * @return this object
     */
    public CreateLoadBalanceRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param dispatchMode
     * @return this object
     */
    public CreateLoadBalanceRequest withDispatchMode(String dispatchMode) {
        this.dispatchMode = dispatchMode;
        return this;
    }

    /**
     * @return dispatchMode
     */
    public String getDispatchMode() {
        return dispatchMode;
    }

    /**
     * @param dispatchMode
     */
    public void setDispatchMode(String dispatchMode) {
        this.dispatchMode = dispatchMode;
    }

    /**
     * @param sessionMode
     * @return this object
     */
    public CreateLoadBalanceRequest withSessionMode(String sessionMode) {
        this.sessionMode = sessionMode;
        return this;
    }

    /**
     * @return sessionMode
     */
    public String getSessionMode() {
        return sessionMode;
    }

    /**
     * @param sessionMode
     */
    public void setSessionMode(String sessionMode) {
        this.sessionMode = sessionMode;
    }

    /**
     * @param detectConfig
     * @return this object
     */
    public CreateLoadBalanceRequest withDetectConfig(DetectConfig detectConfig) {
        this.detectConfig = detectConfig;
        return this;
    }

    /**
     * @return detectConfig
     */
    public DetectConfig getDetectConfig() {
        return detectConfig;
    }

    /**
     * @param detectConfig
     */
    public void setDetectConfig(DetectConfig detectConfig) {
        this.detectConfig = detectConfig;
    }

    /**
     * @param originPoolIds
     * @return this object
     */
    public CreateLoadBalanceRequest withOriginPoolIds(List<String> originPoolIds) {
        this.originPoolIds = originPoolIds;
        return this;
    }

    /**
     * @return originPoolIds
     */
    public List<String> getOriginPoolIds() {
        return originPoolIds;
    }

    /**
     * @param originPoolIds
     */
    public void setOriginPoolIds(List<String> originPoolIds) {
        this.originPoolIds = originPoolIds;
    }

    /**
     * @param weights
     * @return this object
     */
    public CreateLoadBalanceRequest withWeights(List<Integer> weights) {
        this.weights = weights;
        return this;
    }

    /**
     * @return weights
     */
    public List<Integer> getWeights() {
        return weights;
    }

    /**
     * @param weights
     */
    public void setWeights(List<Integer> weights) {
        this.weights = weights;
    }

    /**
     * @param fallbackOriginPoolId
     * @return this object
     */
    public CreateLoadBalanceRequest withFallbackOriginPoolId(String fallbackOriginPoolId) {
        this.fallbackOriginPoolId = fallbackOriginPoolId;
        return this;
    }

    /**
     * @return fallbackOriginPoolId
     */
    public String getFallbackOriginPoolId() {
        return fallbackOriginPoolId;
    }

    /**
     * @param fallbackOriginPoolId
     */
    public void setFallbackOriginPoolId(String fallbackOriginPoolId) {
        this.fallbackOriginPoolId = fallbackOriginPoolId;
    }
}
