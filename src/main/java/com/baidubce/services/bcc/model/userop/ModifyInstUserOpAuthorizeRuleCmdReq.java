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
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.tag.model.Tag;

import java.util.List;

public class ModifyInstUserOpAuthorizeRuleCmdReq extends AbstractBceRequest {
    /**
     * Enable preAuthorization
     */
    private Integer enableRule;

    /**
     * preAuthorization method
     */
    private List<String> authorizeMaintenanceOperations;

    /**
     * The tags of the instance
     */
    private List<Tag> tags;

    /**
     * Authorization scope, AllInstance: all instances; Tags: Specify instances under the specified tag
     */
    private String effectiveScope;

    /**
     * rule name
     */
    private String ruleName;

    /**
     * RuleId to be modified
     */
    private String ruleId;

    public void setEnableRule(Integer enableRule) {
        this.enableRule = enableRule;
    }

    public Integer getEnableRule() {
        return this.enableRule;
    }

    public void setAuthorizeMaintenanceOperations(List<String> authorizeMaintenanceOperations) {
        this.authorizeMaintenanceOperations = authorizeMaintenanceOperations;
    }

    public List<String> getAuthorizeMaintenanceOperations() {
        return this.authorizeMaintenanceOperations;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setEffectiveScope(String effectiveScope) {
        this.effectiveScope = effectiveScope;
    }

    public String getEffectiveScope() {
        return this.effectiveScope;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleId() {
        return this.ruleId;
    }

    @Override
    public String toString() {
        return "ModifyInstUserOpAuthorizeRuleCmdReq{"
                + "enableRule=" + enableRule + "\n"
                + "authorizeMaintenanceOperations=" + authorizeMaintenanceOperations + "\n"
                + "tags=" + tags + "\n"
                + "effectiveScope=" + effectiveScope + "\n"
                + "ruleName=" + ruleName + "\n"
                + "ruleId=" + ruleId + "\n"
                + "}";
    }

    @Override
    public ModifyInstUserOpAuthorizeRuleCmdReq withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}