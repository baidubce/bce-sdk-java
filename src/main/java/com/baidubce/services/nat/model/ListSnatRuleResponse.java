package com.baidubce.services.nat.model;

import com.baidubce.model.ListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The response for listing all snat rules in one specified snat.
 */
@Getter
@Setter
public class ListSnatRuleResponse extends ListResponse {

    /**
     * List of snat rule info
     */
    private List<SnatRule> rules;
}
