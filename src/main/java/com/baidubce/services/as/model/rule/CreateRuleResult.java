package com.baidubce.services.as.model.rule;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
public class CreateRuleResult extends AbstractBceResponse {
    private String ruleId;
}
