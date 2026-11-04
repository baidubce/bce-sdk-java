package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The reference instance of an origin pool.
 */
public class Instance extends JsonObject {
    /**
     * The domain referencing the origin pool.
     */
    private String domain;

    /**
     * The rule engine name referencing the origin pool.
     */
    private String name;

    /**
     * The load balance name referencing the origin pool.
     */
    private String loadBalanceName;

    /**
     * The type of the reference, e.g. "DOMAIN", "PageRules", "loadBalance".
     */
    private String type;

    /**
     * The site that the referencing object belongs to.
     */
    private String site;

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
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
     * @return loadBalanceName
     */
    public String getLoadBalanceName() {
        return loadBalanceName;
    }

    /**
     * @param loadBalanceName
     */
    public void setLoadBalanceName(String loadBalanceName) {
        this.loadBalanceName = loadBalanceName;
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
     * @return site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }
}
