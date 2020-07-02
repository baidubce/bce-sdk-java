package com.baidubce.services.cdn.model.dsa;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class DSA extends JsonObject {
    private List<DSARule> rules;
    private boolean enabled;
    private String comment;

    /**
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @param enabled
     * @return this object
     */
    public DSA withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * @return rules
     */
    public List<DSARule> getRules() {
        return rules;
    }

    /**
     * @param rules rule list
     */
    public void setRules(List<DSARule> rules) {
        this.rules = rules;
    }

    /**
     * @param rules rule list
     * @return this object
     */
    public DSA withRules(List<DSARule> rules) {
        this.rules = rules;
        return this;
    }

    /**
     * @param rule
     * @return this object
     */
    public DSA addRules(DSARule rule) {
        if (this.rules == null) {
            this.rules = new ArrayList<DSARule>();
        }
        this.rules.add(rule);
        return this;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @param comment
     * @return this object
     */
    public DSA withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
