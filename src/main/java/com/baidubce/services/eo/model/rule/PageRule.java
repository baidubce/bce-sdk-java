package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * A single page rule of the rule engine.
 */
public class PageRule extends JsonObject {
    /**
     * The rule name.
     */
    private String name;

    /**
     * The rule status.
     */
    private String status;

    /**
     * The match conditions, a 2-dimensional array.
     */
    private List<List<Rule>> rules;

    /**
     * The config items to apply when the rule matches.
     */
    private Config config;

    /**
     * @param name
     * @return this object
     */
    public PageRule withName(String name) {
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
     * @param status
     * @return this object
     */
    public PageRule withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param rules
     * @return this object
     */
    public PageRule withRules(List<List<Rule>> rules) {
        this.rules = rules;
        return this;
    }

    /**
     * @return rules
     */
    public List<List<Rule>> getRules() {
        return rules;
    }

    /**
     * @param rules
     */
    public void setRules(List<List<Rule>> rules) {
        this.rules = rules;
    }

    /**
     * @param config
     * @return this object
     */
    public PageRule withConfig(Config config) {
        this.config = config;
        return this;
    }

    /**
     * @return config
     */
    public Config getConfig() {
        return config;
    }

    /**
     * @param config
     */
    public void setConfig(Config config) {
        this.config = config;
    }
}
