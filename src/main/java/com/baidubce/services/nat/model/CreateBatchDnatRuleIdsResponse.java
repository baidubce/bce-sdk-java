/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.nat.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBatchDnatRuleIdsResponse extends AbstractBceResponse {
    public List<String> ruleIds;

    @Override
    public String toString() {
        return "CreateBatchDnatRuleIdsResponse{" +
                "ruleIds=" + ruleIds +
                '}';
    }
}
