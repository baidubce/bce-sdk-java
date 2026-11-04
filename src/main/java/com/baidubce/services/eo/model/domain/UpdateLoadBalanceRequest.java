package com.baidubce.services.eo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The request for updating a load balance of a site.
 *
 */
public class UpdateLoadBalanceRequest extends CreateLoadBalanceRequest {
    /**
     * The load balance id to update. It is a path parameter, not serialized into the body.
     */
    @JsonIgnore
    private String id;

    /**
     * @param id
     * @return this object
     */
    public UpdateLoadBalanceRequest withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public UpdateLoadBalanceRequest withSiteId(Integer siteId) {
        super.withSiteId(siteId);
        return this;
    }

    @Override
    public UpdateLoadBalanceRequest withName(String name) {
        super.withName(name);
        return this;
    }

    @Override
    public UpdateLoadBalanceRequest withDispatchMode(String dispatchMode) {
        super.withDispatchMode(dispatchMode);
        return this;
    }

    @Override
    public UpdateLoadBalanceRequest withSessionMode(String sessionMode) {
        super.withSessionMode(sessionMode);
        return this;
    }

    @Override
    public UpdateLoadBalanceRequest withDetectConfig(DetectConfig detectConfig) {
        super.withDetectConfig(detectConfig);
        return this;
    }

    @Override
    public UpdateLoadBalanceRequest withOriginPoolIds(List<String> originPoolIds) {
        super.withOriginPoolIds(originPoolIds);
        return this;
    }

    @Override
    public UpdateLoadBalanceRequest withWeights(List<Integer> weights) {
        super.withWeights(weights);
        return this;
    }

    @Override
    public UpdateLoadBalanceRequest withFallbackOriginPoolId(String fallbackOriginPoolId) {
        super.withFallbackOriginPoolId(fallbackOriginPoolId);
        return this;
    }
}
