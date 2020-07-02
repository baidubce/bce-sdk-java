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

import java.util.Date;

/**
 * The model for alert record.
 */
public class AlertRecordModel {

    /**
     * The id of alert rule.
     */
    private String alertRuleID;

    /**
     * The name of alert rule.
     */
    private String alertRuleName;

    /**
     * The start time.
     */
    private Date startsAt;

    /**
     * The end time.
     */
    private Date endsAt;

    /**
     * The notified time.
     */
    private Date notifiedAt;

    /**
     * The id of alert record.
     */
    private String resourceID;

    /**
     * The expected threshold.
     */
    private int threshold;

    /**
     * The status.
     */
    private String status;

    /**
     * The duration time.
     */
    private String duration;

    /**
     * The PromQL query statement.
     */
    private String promql;

    /**
     * The operator.
     */
    private String op;

    /**
     * The repeat interval time.
     */
    private String repeatInterval;

    /**
     * The contact user.
     */
    private AlertContacts contacts;

    /**
     * The k-v label.
     */
    private Object labels;

    /**
     * The description.
     */
    private String description;

    public String getAlertRuleID() {
        return alertRuleID;
    }

    public void setAlertRuleID(String alertRuleID) {
        this.alertRuleID = alertRuleID;
    }

    public String getAlertRuleName() {
        return alertRuleName;
    }

    public void setAlertRuleName(String alertRuleName) {
        this.alertRuleName = alertRuleName;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public Date getNotifiedAt() {
        return notifiedAt;
    }

    public void setNotifiedAt(Date notifiedAt) {
        this.notifiedAt = notifiedAt;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
