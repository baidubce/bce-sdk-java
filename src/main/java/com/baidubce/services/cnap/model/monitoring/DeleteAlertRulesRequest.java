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
 * The request for delete alert rules.
 */
public class DeleteAlertRulesRequest extends AbstractBceRequest {

    /**
     * The id of alert rule.
     */
    private String alertRuleID;

    public String getAlertRuleID() {
        return alertRuleID;
    }

    public void setAlertRuleID(String alertRuleID) {
        this.alertRuleID = alertRuleID;
    }

    public DeleteAlertRulesRequest withAlertRuleID(String alertRuleID) {
        this.setAlertRuleID(alertRuleID);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public DeleteAlertRulesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
