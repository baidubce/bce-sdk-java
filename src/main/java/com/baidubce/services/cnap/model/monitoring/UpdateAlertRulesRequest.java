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

public class UpdateAlertRulesRequest extends AbstractBceRequest {

    /**
     * The id of alert rule.
     */
    private String alertRuleID;

    /**
     * The duration time.
     */
    private String duration;

    /**
     * The PromQL query
     */
    private String promql;

    /**
     * The operator, eg >、 =、 <
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
    private int threshold = -1;

    public String getAlertRuleID() {
        return alertRuleID;
    }

    public void setAlertRuleID(String alertRuleID) {
        this.alertRuleID = alertRuleID;
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

    public UpdateAlertRulesRequest withAlertRuleID(String alertRuleID) {
        this.setAlertRuleID(alertRuleID);
        return this;
    }

    public UpdateAlertRulesRequest withDuration(String duration) {
        this.setDuration(duration);
        return this;
    }

    public UpdateAlertRulesRequest withPromql(String promql) {
        this.setPromql(promql);
        return this;
    }

    public UpdateAlertRulesRequest withOp(String op) {
        this.setOp(op);
        return this;
    }

    public UpdateAlertRulesRequest withRepeatInterval(String repeatInterval) {
        this.setRepeatInterval(repeatInterval);
        return this;
    }

    public UpdateAlertRulesRequest withContacts(AlertContacts alertContacts) {
        this.setContacts(alertContacts);
        return this;
    }

    public UpdateAlertRulesRequest withLabels(Object labels) {
        this.setLabels(labels);
        return this;
    }

    public UpdateAlertRulesRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public UpdateAlertRulesRequest withThreshold(int threshold) {
        this.setThreshold(threshold);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public UpdateAlertRulesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
