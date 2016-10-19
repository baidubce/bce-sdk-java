package com.baidubce.services.ruleengine.model;

import com.baidubce.model.GenericAccountRequest;

import java.util.List;

/**
 * Created by yuanyoujun on 2016/10/9.
 */
public class DeleteRulesRequest extends GenericAccountRequest {
    private List<String> rules;

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }
}
