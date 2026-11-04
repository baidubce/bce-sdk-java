package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * The load balance detail returned by the query interface.
 */
public class LoadBalanceDetail extends JsonObject {
    /**
     * The load balance id.
     */
    private String loadBalanceId;

    /**
     * The load balance name.
     */
    private String name;

    /**
     * Whether the load balance is referenced.
     */
    private String status;

    /**
     * The load balance strategy.
     */
    private String dispatchMode;

    /**
     * The session persistence.
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
     * The origin pool weights. Non-empty only when the strategy is weighted round robin.
     */
    private List<Integer> weightList;

    /**
     * The fallback origin pool id. Null when not set.
     */
    private String fallbackOriginPoolId;

    /**
     * The number of referenced origin pools.
     */
    private Integer originPoolCount;

    /**
     * The instances that reference the load balance.
     */
    private List<Instance> instance;

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
     * @return weightList
     */
    public List<Integer> getWeightList() {
        return weightList;
    }

    /**
     * @param weightList
     */
    public void setWeightList(List<Integer> weightList) {
        this.weightList = weightList;
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

    /**
     * @return originPoolCount
     */
    public Integer getOriginPoolCount() {
        return originPoolCount;
    }

    /**
     * @param originPoolCount
     */
    public void setOriginPoolCount(Integer originPoolCount) {
        this.originPoolCount = originPoolCount;
    }

    /**
     * @return instance
     */
    public List<Instance> getInstance() {
        return instance;
    }

    /**
     * @param instance
     */
    public void setInstance(List<Instance> instance) {
        this.instance = instance;
    }
}
