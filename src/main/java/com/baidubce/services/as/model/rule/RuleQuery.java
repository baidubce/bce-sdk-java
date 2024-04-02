package com.baidubce.services.as.model.rule;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
public class RuleQuery extends AbstractBceRequest {
    private String ruleId;

    @Override
    public RuleQuery withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
