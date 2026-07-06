package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The status code cache rule of a site.
 *
 */
public class CacheCodeTtl extends JsonObject {
    /**
     * The rule type.
     */
    private String type;

    /**
     * The status code.
     */
    private String value;

    /**
     * The cache time in seconds.
     */
    private Integer ttl;

    /**
     * Whether the cache follows the origin.
     */
    private Boolean overrideOrigin;

    /**
     * The weight.
     */
    private Integer weight;

    /**
     * @param type
     * @return this object
     */
    public CacheCodeTtl withType(String type) {
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

    /**
     * @param value
     * @return this object
     */
    public CacheCodeTtl withValue(String value) {
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
     * @param ttl
     * @return this object
     */
    public CacheCodeTtl withTtl(Integer ttl) {
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
     * @param overrideOrigin
     * @return this object
     */
    public CacheCodeTtl withOverrideOrigin(Boolean overrideOrigin) {
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
     * @param weight
     * @return this object
     */
    public CacheCodeTtl withWeight(Integer weight) {
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
}
