package com.baidubce.services.nat.model;

import com.baidubce.model.ListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The response for listing all dnat rules in one specified dnat.
 */
@Getter
@Setter
public class ListDnatRuleResponse extends ListResponse {
    /**
     * List of dnat rule info
     */
    private List<DnatRule> rules;
}
