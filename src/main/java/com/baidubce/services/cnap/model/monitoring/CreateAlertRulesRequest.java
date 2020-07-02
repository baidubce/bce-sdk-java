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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for create alert rules.
 */
public class CreateAlertRulesRequest extends AbstractBceRequest {

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
     * The operator, eg >、 ==、 <
     */
    private String op;

    /**
     * The interval time of repeat alert.
     */
    private String repeatInterval;

    /**
     * The expected threshold.
     */
    private int threshold = -1;

    /**
     * The contacts user.
     */
    private AlertContacts contacts = new AlertContacts();

    /**
     * The label info which contains k-v pair.
     */
    private Object labels = new Object();

    /**
     * The description info.
     */
    private String description;

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

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
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

    public CreateAlertRulesRequest withName(String name) {
        this.setName(name);
        return this;
    }

    public CreateAlertRulesRequest withDuration(String duration) {
        this.setDuration(duration);
        return this;
    }

    public CreateAlertRulesRequest withPromql(String promql) {
        this.setPromql(promql);
        return this;
    }

    public CreateAlertRulesRequest withOp(String op) {
        this.setOp(op);
        return this;
    }

    public CreateAlertRulesRequest withRepeatInterval(String repeatInterval) {
        this.setRepeatInterval(repeatInterval);
        return this;
    }

    public CreateAlertRulesRequest withThreshold(int threshold) {
        this.setThreshold(threshold);
        return this;
    }

    public CreateAlertRulesRequest withContacts(AlertContacts alertContacts) {
        this.setContacts(alertContacts);
        return this;
    }

    public CreateAlertRulesRequest withLabels(Object labels) {
        this.setLabels(labels);
        return this;
    }

    public CreateAlertRulesRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public CreateAlertRulesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
