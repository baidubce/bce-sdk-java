package com.baidubce.services.cdn.model;

import java.util.List;

public class Dsa {
    private Boolean enabled;

    private List<DsaRule> rules;

    private String comment;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<DsaRule> getRules() {
        return rules;
    }

    public void setRules(List<DsaRule> rules) {
        this.rules = rules;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
