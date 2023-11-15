/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import java.util.List;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteCfwRuleRequest extends BaseBceRequest {
    /**
     * 批量删除的CFW规则id
     */
    private List<String> cfwRuleIds;

    public void setCfwRuleIds(List<String> cfwRuleIds) {
        this.cfwRuleIds = cfwRuleIds;
    }

    public List<String> getCfwRuleIds() {
        return this.cfwRuleIds;
    }

    @Override
    public String toString() {
        return "DeleteCfwRuleRequest{"
                + "cfwRuleIds=" + cfwRuleIds + "\n"
                + "}";
    }

}