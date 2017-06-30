package com.baidubce.services.modbus.model.pullrule;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePullRuleResponse extends AbstractBceResponse {

    private List<PullRule> result;


    public List<PullRule> getResult() {
        return result;
    }


    public void setResult(List<PullRule> result) {
        this.result = result;
    }
}
