package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoRequest;

/**
 * The request for setting the consumption limit of a site or domain.
 */
public class SetLimitConsumptionRequest extends EoRequest {
    /**
     * The site name.
     */
    private String site;

    /**
     * The target object. "site" means the site itself, otherwise it is a domain under the site.
     */
    private String object;

    /**
     * Whether to enable the consumption limit. true to enable, false to disable.
     */
    private Boolean enabled;

    /**
     * The detection type. Valid values are "flow", "bandwidth", "pv".
     */
    private String type;

    /**
     * The detection threshold. The unit depends on type: "MB"/"GB"/"TB" for flow,
     * "Mbps"/"Gbps"/"Tbps" for bandwidth, a plain number for pv.
     */
    private String threshold;

    /**
     * The detection cycle in seconds. Required when enabled is true. Valid values are 300, 3600,
     * 86400. It is fixed to 300 when type is "bandwidth".
     */
    private Integer statisticalCycle;

    /**
     * The action triggered when reaching the threshold. Fixed value "stop".
     */
    private String action;

    /**
     * @param site
     * @return this object
     */
    public SetLimitConsumptionRequest withSite(String site) {
        this.site = site;
        return this;
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

    /**
     * @param object
     * @return this object
     */
    public SetLimitConsumptionRequest withObject(String object) {
        this.object = object;
        return this;
    }

    /**
     * @return object
     */
    public String getObject() {
        return object;
    }

    /**
     * @param object
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * @param enabled
     * @return this object
     */
    public SetLimitConsumptionRequest withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @param type
     * @return this object
     */
    public SetLimitConsumptionRequest withType(String type) {
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
     * @param threshold
     * @return this object
     */
    public SetLimitConsumptionRequest withThreshold(String threshold) {
        this.threshold = threshold;
        return this;
    }

    /**
     * @return threshold
     */
    public String getThreshold() {
        return threshold;
    }

    /**
     * @param threshold
     */
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    /**
     * @param statisticalCycle
     * @return this object
     */
    public SetLimitConsumptionRequest withStatisticalCycle(Integer statisticalCycle) {
        this.statisticalCycle = statisticalCycle;
        return this;
    }

    /**
     * @return statisticalCycle
     */
    public Integer getStatisticalCycle() {
        return statisticalCycle;
    }

    /**
     * @param statisticalCycle
     */
    public void setStatisticalCycle(Integer statisticalCycle) {
        this.statisticalCycle = statisticalCycle;
    }

    /**
     * @param action
     * @return this object
     */
    public SetLimitConsumptionRequest withAction(String action) {
        this.action = action;
        return this;
    }

    /**
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }
}
