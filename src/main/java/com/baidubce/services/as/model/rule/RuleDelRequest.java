package com.baidubce.services.as.model.rule;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDelRequest extends AbstractBceRequest {
    private List<String> ruleIds;
    private List<String> groupIds;

    @Override
    public RuleDelRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
