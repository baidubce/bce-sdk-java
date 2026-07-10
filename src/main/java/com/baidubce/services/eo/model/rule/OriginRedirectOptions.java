package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The origin 301/302 redirect follow configuration.
 */
public class OriginRedirectOptions extends JsonObject {
    /**
     * "ON" means enabled, "OFF" means disabled.
     */
    private String enableRedirectFollow;

    /**
     * The max redirect follow count, range 1-5.
     */
    private Integer maxRedirectFollowCount;

    /**
     * @param enableRedirectFollow
     * @return this object
     */
    public OriginRedirectOptions withEnableRedirectFollow(String enableRedirectFollow) {
        this.enableRedirectFollow = enableRedirectFollow;
        return this;
    }

    /**
     * @return enableRedirectFollow
     */
    public String getEnableRedirectFollow() {
        return enableRedirectFollow;
    }

    /**
     * @param enableRedirectFollow
     */
    public void setEnableRedirectFollow(String enableRedirectFollow) {
        this.enableRedirectFollow = enableRedirectFollow;
    }

    /**
     * @param maxRedirectFollowCount
     * @return this object
     */
    public OriginRedirectOptions withMaxRedirectFollowCount(Integer maxRedirectFollowCount) {
        this.maxRedirectFollowCount = maxRedirectFollowCount;
        return this;
    }

    /**
     * @return maxRedirectFollowCount
     */
    public Integer getMaxRedirectFollowCount() {
        return maxRedirectFollowCount;
    }

    /**
     * @param maxRedirectFollowCount
     */
    public void setMaxRedirectFollowCount(Integer maxRedirectFollowCount) {
        this.maxRedirectFollowCount = maxRedirectFollowCount;
    }
}
