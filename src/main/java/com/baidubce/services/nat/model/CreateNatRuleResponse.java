package com.baidubce.services.nat.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * The response for creating a nat rule.
 */
@Getter
@Setter
public class CreateNatRuleResponse extends AbstractBceResponse {

    /**
     * the id of nat rule newly created.
     */
    private String ruleId;
}
