/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bos.model;

import java.util.List;

/**
 * The Rule for Bucket Lifecycle.
 */
public class Rule {

    /**
     * The identifier of the rule.
     * The bucket id must be unique within the same bucket and cannot be duplicated.
     * If the user does not fill in the system will automatically generate one for the user.
     */
    private String id;

    /**
     * The status of the rule.
     * The value is either enabled or disabled.
     * When the rule is disabled, the rule does not take effect.
     */
    private String status;

    /**
     * The resource of the rule.
     */
    private List<String> resource;

    /**
     * Rules depend on the conditions.
     * Currently only the time format is supported.
     */
    private Condition condition;

    /**
     * The operation action performed on the resource.
     * Include Required param name.(The name of the operation performed),
     * The name value is Transition、DeleteObject、AbortMultipartUpload.
     * Include Include param storageClass(Object storage type),
     * When the action is Transition, it can be set to STANDARD_IA or COLD,
     * which means it changes from the original storage type to low frequency storage or cold storage.
     */
    private Action action;

    /**
     * Gets the rule id of Bucket Lifecycle.
     * @return the rule id of Bucket Lifecycle.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the rule id of Bucket Lifecycle.
     * @param id The rule id of Bucket Lifecycle.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the rule id of Bucket Lifecycle.
     * @param id The rule id of Bucket Lifecycle.
     * @return this object.
     */
    public Rule withId(String id) {
        this.setId(id);
        return this;
    }

    /**
     * Gets the status of Bucket Lifecycle.
     * @return the status of Bucket Lifecycle.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of Bucket Lifecycle.
     * @param status The status of Bucket Lifecycle.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the status of Bucket Lifecycle.
     * @param status The status of Bucket Lifecycle.
     * @return this object.
     */
    public Rule withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    /**
     * Which resources the rules take effect.
     *
     */
    public List<String> getResource() {
        return resource;
    }

    /**
     * Sets the resource of Bucket Lifecycle.
     * @param resource The resource of Bucket Lifecycle.
     */
    public void setResource(List<String> resource) {
        this.resource = resource;
    }

    /**
     * Sets the resource of Bucket Lifecycle.
     * @param resource The resources of Bucket Lifecycle.
     * @return this object.
     */
    public Rule withResource(List<String> resource) {
        this.setResource(resource);
        return this;
    }


    /**
     * Gets the condtion of Bucket Lifecycle.
     * @return the condtion of Bucket Lifecycle.
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Sets the condtion of Bucket Lifecycle.
     * @param condition The condtion of Bucket Lifecycle.
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    /**
     * Sets the condtion of Bucket Lifecycle.
     * @param condition The condtion of Bucket Lifecycle.
     * @return this object.
     */
    public Rule withCondtion(Condition condition) {
        this.setCondition(condition);
        return this;
    }

    /**
     * Gets the action of Bucket Lifecycle.
     * @return the action of Bucket Lifecycle.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Sets the action of Bucket Lifecycle.
     * @param action The action of Bucket Lifecycle.
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Sets the action of Bucket Lifecycle.
     * @param action The action of Bucket Lifecycle.
     * @return this object.
     */
    public Rule withAction(Action action) {
        this.setAction(action);
        return this;
    }

    @Override
    public String toString() {
        return "Rule{"
                + "id='" + id + '\''
                + ", status='" + status + '\''
                + ", resource=" + resource
                + ", condtion=" + condition
                + ", action=" + action
                + '}';
    }
}
