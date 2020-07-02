package com.baidubce.services.cdn.model.dsa;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class DSADomain extends JsonObject {
    private String domain;
    private List<DSARule> rules;
    private String modifyTime;
    private String comment;

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public DSADomain withDomain(String domain) {
        this.domain = domain;
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
    public DSADomain withRules(List<DSARule> rules) {
        this.rules = rules;
        return this;
    }

    /**
     * @param rule
     * @return this object
     */
    public DSADomain addRules(DSARule rule) {
        if (this.rules == null) {
            this.rules = new ArrayList<DSARule>();
        }
        this.rules.add(rule);
        return this;
    }

    /**
     * @return modifyTime
     */
    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @param modifyTime
     * @return this object
     */
    public DSADomain withModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
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
    public DSADomain withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
