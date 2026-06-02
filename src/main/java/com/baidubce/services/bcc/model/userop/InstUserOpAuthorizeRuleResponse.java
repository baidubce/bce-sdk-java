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

import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class InstUserOpAuthorizeRuleResponse {
    /**
     * ruleId
     */
    private String ruleId;

    /**
     * rule name
     */
    private String ruleName;

    /**
     * PreAuthorization event classification
     */
    private String serverEventCategory;

    /**
     * Scope of rule association
     */
    private String effectiveScope;

    /**
     * PreAuthorization status
     */
    private String status;

    /**
     * The tags of the instance
     */
    private List<Tag> tags;

    /**
     * Authorization method
     */
    private List<String> authorizeMaintenanceOperations;

    /**
     * Create time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Date createTime;

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleId() {
        return this.ruleId;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setServerEventCategory(String serverEventCategory) {
        this.serverEventCategory = serverEventCategory;
    }

    public String getServerEventCategory() {
        return this.serverEventCategory;
    }

    public void setEffectiveScope(String effectiveScope) {
        this.effectiveScope = effectiveScope;
    }

    public String getEffectiveScope() {
        return this.effectiveScope;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setAuthorizeMaintenanceOperations(List<String> authorizeMaintenanceOperations) {
        this.authorizeMaintenanceOperations = authorizeMaintenanceOperations;
    }

    public List<String> getAuthorizeMaintenanceOperations() {
        return this.authorizeMaintenanceOperations;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    @Override
    public String toString() {
        return "InstUserOpAuthorizeRuleResponse{"
                + "ruleId=" + ruleId + "\n"
                + "ruleName=" + ruleName + "\n"
                + "serverEventCategory=" + serverEventCategory + "\n"
                + "effectiveScope=" + effectiveScope + "\n"
                + "status=" + status + "\n"
                + "tags=" + tags + "\n"
                + "authorizeMaintenanceOperations=" + authorizeMaintenanceOperations + "\n"
                + "createTime=" + createTime + "\n"
                + "}";
    }

}