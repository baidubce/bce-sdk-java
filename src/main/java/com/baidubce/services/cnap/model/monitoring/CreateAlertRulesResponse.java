/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.cnap.model.monitoring;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response for create alert rules.
 */
public class CreateAlertRulesResponse extends AbstractBceResponse {

    /**
     * The id of resource.
     */
    private String resourceID;

    /**
     * The name of alert rule.
     */
    private String name;

    /**
     * The duration time.
     */
    private String duration;

    /**
     * The PromQL query
     */
    private String promql;

    /**
     * The operator.
     */
    private String op;

    /**
     * The interval time of repeat alert.
     */
    private String repeatInterval;

    /**
     * The contacts user.
     */
    private AlertContacts contacts;

    /**
     * The label info which contains k-v pair.
     */
    private Object labels;

    /**
     * The description info.
     */
    private String description;

    /**
     * The expected threshold.
     */
    private int threshold;

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPromql() {
        return promql;
    }

    public void setPromql(String promql) {
        this.promql = promql;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(String repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public AlertContacts getContacts() {
        return contacts;
    }

    public void setContacts(AlertContacts contacts) {
        this.contacts = contacts;
    }

    public Object getLabels() {
        return labels;
    }

    public void setLabels(Object labels) {
        this.labels = labels;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
