package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * A single origin pool entry inside a load balance.
 */
public class Origins extends JsonObject {
    /**
     * The origin configurations of this origin pool.
     */
    private List<OriginConfig> originConfig;

    /**
     * The origin pool weight. Present when the load balance strategy is weighted round robin.
     */
    private Integer originPoolWeight;

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
     * @return originPoolWeight
     */
    public Integer getOriginPoolWeight() {
        return originPoolWeight;
    }

    /**
     * @param originPoolWeight
     */
    public void setOriginPoolWeight(Integer originPoolWeight) {
        this.originPoolWeight = originPoolWeight;
    }
}
