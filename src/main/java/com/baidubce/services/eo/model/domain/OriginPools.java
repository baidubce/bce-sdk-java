package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * The origin configuration of a load-balance-backed domain.
 */
public class OriginPools extends JsonObject {
    /**
     * The origins of the load balance.
     */
    private List<Origins> origins;

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
     * The fallback origin pool origins. Empty when not set.
     */
    private List<OriginConfig> fallbackOriginConfig;

    /**
     * @return origins
     */
    public List<Origins> getOrigins() {
        return origins;
    }

    /**
     * @param origins
     */
    public void setOrigins(List<Origins> origins) {
        this.origins = origins;
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
     * @return fallbackOriginConfig
     */
    public List<OriginConfig> getFallbackOriginConfig() {
        return fallbackOriginConfig;
    }

    /**
     * @param fallbackOriginConfig
     */
    public void setFallbackOriginConfig(List<OriginConfig> fallbackOriginConfig) {
        this.fallbackOriginConfig = fallbackOriginConfig;
    }
}
