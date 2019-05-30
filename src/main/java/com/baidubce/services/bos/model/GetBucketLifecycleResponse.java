package com.baidubce.services.bos.model;

import java.util.List;

/**
 * Get Bucket Lifecycle Response
 */
public class GetBucketLifecycleResponse extends BosResponse {

    /**
     * rule of GetBucketLifecycleResponse.
     */
    private List<Rule> rule;

    /**
     * Gets rule of GetBucketLifecycleResponse.
     * @return rule of GetBucketLifecycleResponse.
     */
    public List<Rule> getRule() {
        return rule;
    }

    /**
     * Sets rule of GetBucketLifecycleResponse.
     * @param rule The rule of GetBucketLifecycleResponse.
     */
    public void setRule(List<Rule> rule) {
        this.rule = rule;
    }

    /**
     * Constructs a void constructor for GetBucketLifecycleResponse.
     */
    public GetBucketLifecycleResponse() {
    }

    /**
     * Constructs a new GetBucketLifecycleResponse object for GetBucketLifecycleResponse.
     * @param rule
     */
    public GetBucketLifecycleResponse(List<Rule> rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "GetBucketLifecycleResponse{"
                + "rule=" + rule
                + '}';
    }
}
