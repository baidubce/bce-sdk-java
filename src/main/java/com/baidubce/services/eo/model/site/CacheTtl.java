package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The cache TTL rule of a site node cache configuration.
 */
public class CacheTtl extends JsonObject {
    private String value;
    private Integer weight;
    @JsonProperty("override_origin")
    private Boolean overrideOrigin;
    private Integer ttl;
    private String type;

    /**
     * @param value
     * @return this object
     */
    public CacheTtl withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param weight
     * @return this object
     */
    public CacheTtl withWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    /**
     * @return weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @param overrideOrigin
     * @return this object
     */
    public CacheTtl withOverrideOrigin(Boolean overrideOrigin) {
        this.overrideOrigin = overrideOrigin;
        return this;
    }

    /**
     * @return overrideOrigin
     */
    public Boolean getOverrideOrigin() {
        return overrideOrigin;
    }

    /**
     * @param overrideOrigin
     */
    public void setOverrideOrigin(Boolean overrideOrigin) {
        this.overrideOrigin = overrideOrigin;
    }

    /**
     * @param ttl
     * @return this object
     */
    public CacheTtl withTtl(Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * @return ttl
     */
    public Integer getTtl() {
        return ttl;
    }

    /**
     * @param ttl
     */
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    /**
     * @param type
     * @return this object
     */
    public CacheTtl withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
