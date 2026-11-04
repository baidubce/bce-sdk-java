package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The origin pool configuration of a site.
 *
 */
public class OriginPool extends JsonObject {
    /**
     * The origin pool name. Supports letters, digits and some special characters, length 0-120.
     */
    private String name;

    /**
     * The origin configurations.
     */
    private List<OriginConfig> originConfig;

    /**
     * @param name
     * @return this object
     */
    public OriginPool withName(String name) {
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
     * @param originConfig
     * @return this object
     */
    public OriginPool withOriginConfig(List<OriginConfig> originConfig) {
        this.originConfig = originConfig;
        return this;
    }

    /**
     * @param config
     * @return this object
     */
    public OriginPool addOriginConfig(OriginConfig config) {
        if (this.originConfig == null) {
            this.originConfig = new ArrayList<OriginConfig>();
        }
        this.originConfig.add(config);
        return this;
    }

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
}
