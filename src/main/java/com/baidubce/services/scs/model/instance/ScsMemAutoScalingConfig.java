package com.baidubce.services.scs.model.instance;

/**
 * Scs memory auto scaling config.
 */
public class ScsMemAutoScalingConfig {

    private Integer memUsageUpperThreshold;
    private Integer memUsageDownThreshold;
    private String maxNodeType;
    private String minNodeType;
    private String observationWindowSizeForUpper;
    private String observationWindowSizeForDown;

    public Integer getMemUsageUpperThreshold() {
        return memUsageUpperThreshold;
    }

    public void setMemUsageUpperThreshold(Integer memUsageUpperThreshold) {
        this.memUsageUpperThreshold = memUsageUpperThreshold;
    }

    public Integer getMemUsageDownThreshold() {
        return memUsageDownThreshold;
    }

    public void setMemUsageDownThreshold(Integer memUsageDownThreshold) {
        this.memUsageDownThreshold = memUsageDownThreshold;
    }

    public String getMaxNodeType() {
        return maxNodeType;
    }

    public void setMaxNodeType(String maxNodeType) {
        this.maxNodeType = maxNodeType;
    }

    public String getMinNodeType() {
        return minNodeType;
    }

    public void setMinNodeType(String minNodeType) {
        this.minNodeType = minNodeType;
    }

    public String getObservationWindowSizeForUpper() {
        return observationWindowSizeForUpper;
    }

    public void setObservationWindowSizeForUpper(String observationWindowSizeForUpper) {
        this.observationWindowSizeForUpper = observationWindowSizeForUpper;
    }

    public String getObservationWindowSizeForDown() {
        return observationWindowSizeForDown;
    }

    public void setObservationWindowSizeForDown(String observationWindowSizeForDown) {
        this.observationWindowSizeForDown = observationWindowSizeForDown;
    }
}
