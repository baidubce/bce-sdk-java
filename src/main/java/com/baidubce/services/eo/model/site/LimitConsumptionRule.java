package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

/**
 * A single consumption limit rule.
 */
public class LimitConsumptionRule extends JsonObject {
    /**
     * The target object. "site" means the site itself, otherwise it is a domain under the site.
     */
    private String object;

    /**
     * Whether the consumption limit is enabled.
     */
    private Boolean enabled;

    /**
     * Whether the threshold has been reached and the action has been triggered.
     */
    private Boolean activated;

    /**
     * The detection type. Valid values are "flow", "bandwidth", "pv".
     */
    private String type;

    /**
     * The detection threshold. The unit depends on type.
     */
    private String threshold;

    /**
     * The detection cycle in seconds. Valid values are 300, 3600, 86400.
     */
    private Integer statisticalCycle;

    /**
     * The action triggered when reaching the threshold. Fixed value "stop".
     */
    private String action;

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
     * @return activated
     */
    public Boolean getActivated() {
        return activated;
    }

    /**
     * @param activated
     */
    public void setActivated(Boolean activated) {
        this.activated = activated;
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
