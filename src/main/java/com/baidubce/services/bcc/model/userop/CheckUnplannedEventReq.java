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

public class CheckUnplannedEventReq extends AbstractBceRequest {
    /**
     * event Id
     */
    private String serverEventId;

    /**
     * Whether unplanned events have passed the acceptance, supporting parameters Pass and Reject
     */
    private String checkResult;

    /**
     * failure effect
     */
    private String issueEffect;

    /**
     * Fault description must be passed when the fault acceptance is rejected
     */
    private String issueDescription;

    /**
     * Authorized operation and maintenance must be passed when the fault acceptance is rejected
     */
    private String authorizeMaintenanceOperation;

    public void setServerEventId(String serverEventId) {
        this.serverEventId = serverEventId;
    }

    public String getServerEventId() {
        return this.serverEventId;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckResult() {
        return this.checkResult;
    }

    public void setIssueEffect(String issueEffect) {
        this.issueEffect = issueEffect;
    }

    public String getIssueEffect() {
        return this.issueEffect;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueDescription() {
        return this.issueDescription;
    }

    public void setAuthorizeMaintenanceOperation(String authorizeMaintenanceOperation) {
        this.authorizeMaintenanceOperation = authorizeMaintenanceOperation;
    }

    public String getAuthorizeMaintenanceOperation() {
        return this.authorizeMaintenanceOperation;
    }

    @Override
    public String toString() {
        return "CheckUnplannedEventReq{"
                + "serverEventId=" + serverEventId + "\n"
                + "checkResult=" + checkResult + "\n"
                + "issueEffect=" + issueEffect + "\n"
                + "issueDescription=" + issueDescription + "\n"
                + "authorizeMaintenanceOperation=" + authorizeMaintenanceOperation + "\n"
                + "}";
    }

    @Override
    public CheckUnplannedEventReq withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}