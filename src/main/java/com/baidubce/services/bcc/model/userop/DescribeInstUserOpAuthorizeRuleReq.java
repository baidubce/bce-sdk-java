/*
 * Copyright 2025 Baidu, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc.model.userop;

import com.baidubce.auth.BceCredentials;

import java.util.List;


public class DescribeInstUserOpAuthorizeRuleReq extends ListRequest {

    /**
     * ruleId to be queried
     */
    private List<String> ruleIds;

    /**
     * Name of the rule to be queried
     */
    private List<String> ruleNames;

    public void setRuleIds(List<String> ruleIds) {
        this.ruleIds = ruleIds;
    }

    public List<String> getRuleIds() {
        return this.ruleIds;
    }

    public void setRuleNames(List<String> ruleNames) {
        this.ruleNames = ruleNames;
    }

    public List<String> getRuleNames() {
        return this.ruleNames;
    }

    @Override
    public String toString() {
        return "DescribeInstUserOpAuthorizeRuleV3Req{"
                + "ruleIds=" + ruleIds + "\n"
                + "ruleNames=" + ruleNames + "\n"
                + "}";
    }

    @Override
    public DescribeInstUserOpAuthorizeRuleReq withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}